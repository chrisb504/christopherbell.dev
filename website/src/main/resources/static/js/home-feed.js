function authHeaders() {
  const token = localStorage.getItem('cbellLoginToken');
  return token ? { Authorization: `Bearer ${token}` } : {};
}

async function fetchJson(url, options = {}) {
  const resp = await fetch(url, {
    ...options,
    headers: { 'Content-Type': 'application/json', ...(options.headers || {}) },
  });
  const data = await resp.json().catch(() => ({}));
  if (!resp.ok || data.success === false) {
    const msg = data?.messages?.[0]?.description || `Request failed: ${resp.status}`;
    throw new Error(msg);
  }
  return data.payload ?? data;
}

function sanitize(text) {
  return (text || '').replace(/</g, '&lt;').replace(/>/g, '&gt;');
}

function updateCounter(el, counter) {
  const len = (el.value || '').length;
  counter.textContent = `${len} / 280`;
}

function setComposerEnabled(enabled) {
  const postBtn = document.getElementById('postBtn');
  const postText = document.getElementById('postText');
  if (postBtn) postBtn.disabled = !enabled;
  if (postText) postText.disabled = !enabled;
}

let FEED_STATE = { before: null, limit: 20, loading: false, done: false, latest: null };
let USER_STATE = { id: null, role: null, username: null };
const ROOT_CACHE = {};

async function loadFeed(initial = false) {
  const feedList = document.getElementById('feedList');
  if (!feedList) return;
  if (initial) {
    FEED_STATE = { before: null, limit: 20, loading: false, done: false, latest: null };
    feedList.innerHTML = '';
  }
  if (FEED_STATE.loading || FEED_STATE.done) return;
  FEED_STATE.loading = true;
  try {
    const params = new URLSearchParams();
    params.set('limit', FEED_STATE.limit.toString());
    if (FEED_STATE.before) params.set('before', FEED_STATE.before);
    const posts = await fetchJson(`/api/posts/2025-09-14/feed?${params.toString()}`);
    if (!posts || posts.length === 0) {
      if (feedList.children.length === 0) {
        feedList.innerHTML = '<div class="list-group-item">No posts yet.</div>';
      }
      FEED_STATE.done = true;
      FEED_STATE.loading = false;
      return;
    }
    for (const p of posts) {
      const item = document.createElement('div');
      item.className = 'list-group-item py-3';
      const when = new Date(p.createdOn || p.lastUpdatedOn || Date.now()).toLocaleString();
      const handle = p.username ? `@${sanitize(p.username)}` : '@user';
      const canDelete = USER_STATE && USER_STATE.id && (USER_STATE.role === 'ADMIN' || USER_STATE.id === p.accountId);
      item.innerHTML = `
        <div class="d-flex w-100 justify-content-between align-items-start">
          <div class="w-100">
            <div class="fw-semibold"><a href="/u/${encodeURIComponent(p.username)}" class="link-underline link-underline-opacity-0">${handle}</a></div>
            <p class="mb-1 fs-5"><a href="/p/${encodeURIComponent(p.id)}" class="link-underline link-underline-opacity-0 text-body">${sanitize(p.text)}</a></p>
          </div>
          <div class="ms-3 text-end flex-shrink-0 position-relative">
            <small class="text-muted d-block">${when}</small>
            ${canDelete ? `
            <button class="btn btn-sm btn-light post-menu-btn" data-post="${p.id}" aria-label="More">⋯</button>
            <div class="post-menu d-none card p-2" style="position:absolute; right:0; top:100%; z-index:1000;">
              <button class="btn btn-link text-danger p-0 post-delete-btn" data-post="${p.id}">Delete</button>
            </div>` : ''}
          </div>
        </div>
        ${p.level && p.level > 0 && p.rootId ? `<div class="parent-context card mt-2 w-100">
            <div class="card-body py-2">
              <div class="fw-semibold"><a href="/u/" class="link-underline link-underline-opacity-0" data-root-handle="${p.rootId}">@user</a></div>
              <p class="mb-0 fs-4 fw-semibold" data-root="${p.rootId}">Loading…</p>
              <a href="/p/${encodeURIComponent(p.rootId)}" class="small">View thread</a>
            </div>
          </div>` : ''}
      `;
      feedList.appendChild(item);
      // Load root context if needed
      if (p.level && p.level > 0 && p.rootId) {
        const ctx = item.querySelector(`[data-root="${p.rootId}"]`);
        const handleEl = item.querySelector(`[data-root-handle="${p.rootId}"]`);
        if (ctx) {
          (async () => {
            try {
              let root = ROOT_CACHE[p.rootId];
              if (!root) {
                root = await fetchJson(`/api/posts/2025-09-14/${encodeURIComponent(p.rootId)}`);
                ROOT_CACHE[p.rootId] = root;
              }
              const h = root.username ? `@${sanitize(root.username)}` : '@user';
              if (handleEl) {
                handleEl.textContent = h;
                handleEl.setAttribute('href', `/u/${encodeURIComponent(root.username)}`);
              }
              ctx.textContent = root.text ? `${root.text}` : '';
            } catch (_) { ctx.textContent = 'Context unavailable'; }
          })();
        }
      }
      // Wire menu toggle and delete
      const btn = item.querySelector('.post-menu-btn');
      const menu = item.querySelector('.post-menu');
      if (btn && menu) {
        btn.addEventListener('click', (e) => {
          e.stopPropagation();
          menu.classList.toggle('d-none');
        });
        const del = item.querySelector('.post-delete-btn');
        del?.addEventListener('click', async (e) => {
          e.stopPropagation();
          menu.classList.add('d-none');
          if (!confirm('Delete this post?')) return;
          try {
            await fetchJson(`/api/posts/2025-09-14/${btn.dataset.post}`, { method: 'DELETE', headers: authHeaders() });
            item.remove();
          } catch (err) {
            alert(err.message);
          }
        });
      }
    }
    // Update paging cursor to last item's createdOn
    const last = posts[posts.length - 1];
    FEED_STATE.before = last.createdOn || last.lastUpdatedOn;
    // Track newest timestamp from the first item of the initial (or any newer) load
    const first = posts[0];
    const newestTs = first.createdOn || first.lastUpdatedOn;
    if (!FEED_STATE.latest || (new Date(newestTs) > new Date(FEED_STATE.latest))) {
      FEED_STATE.latest = newestTs;
    }
    if (posts.length < FEED_STATE.limit) FEED_STATE.done = true;
  } catch (err) {
    const feedList = document.getElementById('feedList');
    if (feedList) {
      feedList.innerHTML = `<div class="list-group-item text-danger">${sanitize(err.message)}</div>`;
    }
  }
  FEED_STATE.loading = false;
}

async function submitPost() {
  const postText = document.getElementById('postText');
  const alert = document.getElementById('homeAlert');
  if (alert) alert.classList.add('d-none');
  const text = (postText?.value || '').trim();
  if (!text) return;
  if (text.length > 280) {
    if (alert) {
      alert.textContent = 'Post text exceeds 280 characters.';
      alert.classList.remove('d-none');
    }
    return;
  }
  try {
    await fetchJson('/api/posts/2025-09-14/create', {
      method: 'POST',
      headers: authHeaders(),
      body: JSON.stringify({ text })
    });
    if (postText) postText.value = '';
    const counter = document.getElementById('charCount');
    if (counter && postText) updateCounter(postText, counter);
    await loadFeed();
  } catch (err) {
    if (alert) {
      alert.textContent = err.message;
      alert.classList.remove('d-none');
    }
  }
}

document.addEventListener('DOMContentLoaded', async () => {
  // Load current user to determine delete permissions before first render
  const token = localStorage.getItem('cbellLoginToken');
  // Toggle composer vs prompt depending on auth state
  const composerEl = document.getElementById('composer');
  const promptEl = document.getElementById('composerPrompt');
  if (token) {
    composerEl?.classList.remove('d-none');
    promptEl?.classList.add('d-none');
  } else {
    composerEl?.classList.add('d-none');
    promptEl?.classList.remove('d-none');
  }
  if (token) {
    try {
      const me = await fetchJson('/api/accounts/2025-09-03/me', { headers: authHeaders() });
      USER_STATE = { id: me.id, role: me.role, username: me.username };
    } catch (_) { /* ignore */ }
  }
  // Close any open menus on outside click
  document.addEventListener('click', () => {
    document.querySelectorAll('.post-menu').forEach(m => m.classList.add('d-none'));
  });
  const postText = document.getElementById('postText');
  const counter = document.getElementById('charCount');
  if (postText && counter) {
    updateCounter(postText, counter);
    postText.addEventListener('input', () => updateCounter(postText, counter));
  }
  const postBtn = document.getElementById('postBtn');
  if (postBtn) postBtn.addEventListener('click', async () => {
    await submitPost();
    // After posting, refresh to top of feed
    await loadFeed(true);
    window.scrollTo({ top: 0, behavior: 'smooth' });
  });
  // Show new posts banner when available
  const banner = document.getElementById('newPostsBanner');
  const showBtn = document.getElementById('showNewPostsBtn');
  if (showBtn) {
    showBtn.addEventListener('click', async () => {
      if (banner) banner.classList.add('d-none');
      await loadFeed(true);
      window.scrollTo({ top: 0, behavior: 'smooth' });
    });
  }
  await loadFeed(true);
  // Infinite scroll - load next page when near bottom
  window.addEventListener('scroll', () => {
    const nearBottom = window.innerHeight + window.scrollY >= document.body.offsetHeight - 200;
    if (nearBottom) {
      loadFeed(false);
    }
  });
  // Poll for new posts every 15 seconds
  setInterval(async () => {
    try {
      const latest = await fetchJson('/api/posts/2025-09-14/feed?limit=1');
      const top = latest && latest[0];
      if (!top) return;
      const topTs = top.createdOn || top.lastUpdatedOn;
      if (FEED_STATE.latest && new Date(topTs) > new Date(FEED_STATE.latest)) {
        if (banner) banner.classList.remove('d-none');
      }
    } catch (_) { /* ignore polling failures */ }
  }, 15000);
});
