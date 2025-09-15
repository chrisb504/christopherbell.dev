const alertBox = () => document.getElementById('profileAlert');

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

function renderAccount(detail) {
  const root = document.getElementById('accountDetails');
  if (!root) return;
  const set = (name, value) => {
    const el = root.querySelector(`[data-field="${name}"]`);
    if (el) el.textContent = value ?? '—';
  };
  set('username', detail.username);
  set('email', detail.email);
  const fullName = [detail.firstName, detail.lastName].filter(Boolean).join(' ').trim();
  set('name', fullName || '—');
  set('role', detail.role);
  set('status', detail.status);
}

function sanitize(text) { return (text || '').replace(/</g, '&lt;').replace(/>/g, '&gt;'); }

const ROOT_CACHE = {};

function renderPosts(posts, username) {
  const container = document.getElementById('postsList');
  if (!container) return;
  container.innerHTML = '';
  if (!posts || posts.length === 0) {
    container.innerHTML = '<div class="list-group-item">No posts yet.</div>';
    return;
  }
  for (const p of posts) {
    const item = document.createElement('div');
    item.className = 'list-group-item py-3';
    const when = new Date(p.createdOn || p.lastUpdatedOn || Date.now()).toLocaleString();
    const handle = username ? `@${sanitize(username)}` : '@me';
    // On /profile, posts are always authored by the current user, so allow delete
    item.innerHTML = `
      <div class="d-flex w-100 justify-content-between align-items-start">
        <div>
          <div class="fw-semibold">${handle}</div>
          ${p.level && p.level > 0 && p.rootId ? `<div class="border rounded p-2 mb-2 bg-light-subtle small">
              <span class="text-muted">In reply to</span>
              <a href="/p/${encodeURIComponent(p.rootId)}" class="ms-1">view thread</a>
              <div class="mt-1" data-root="${p.rootId}">Loading context…</div>
            </div>` : ''}
          <p class="mb-1 fs-5">${sanitize(p.text)}</p>
        </div>
        <div class="ms-3 text-end flex-shrink-0 position-relative">
          <small class="text-muted d-block">${when}</small>
          <button class="btn btn-sm btn-light post-menu-btn" data-post="${p.id}" aria-label="More">⋯</button>
          <div class="post-menu d-none card p-2" style="position:absolute; right:0; top:100%; z-index:1000;">
            <button class="btn btn-link text-danger p-0 post-delete-btn" data-post="${p.id}">Delete</button>
          </div>
        </div>
      </div>
    `;
    container.appendChild(item);
    if (p.level && p.level > 0 && p.rootId) {
      const ctx = item.querySelector(`[data-root="${p.rootId}"]`);
      if (ctx) {
        (async () => {
          try {
            let root = ROOT_CACHE[p.rootId];
            if (!root) {
              root = await fetchJson(`/api/posts/2025-09-14/${encodeURIComponent(p.rootId)}`);
              ROOT_CACHE[p.rootId] = root;
            }
            const h = root.username ? `@${sanitize(root.username)}` : '@user';
            ctx.innerHTML = `<a href="/u/${encodeURIComponent(root.username)}">${h}</a>: ${sanitize(root.text)}`;
          } catch (_) { ctx.textContent = 'Context unavailable'; }
        })();
      }
    }
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
}

document.addEventListener('DOMContentLoaded', async () => {
  const token = localStorage.getItem('cbellLoginToken');
  if (!token) {
    // Must be logged in
    window.location.href = '/login';
    return;
  }
  const alert = alertBox();
  alert?.classList.add('d-none');
  try {
    const me = await fetchJson('/api/accounts/2025-09-03/me', { headers: authHeaders() });
    renderAccount(me);
    const posts = await fetchJson('/api/posts/2025-09-14/me', { headers: authHeaders() });
    renderPosts(posts, me?.username);
  } catch (err) {
    if (alert) {
      alert.textContent = err.message;
      alert.classList.remove('d-none');
    }
  }
  // Close menus on outside click
  document.addEventListener('click', () => {
    document.querySelectorAll('.post-menu').forEach(m => m.classList.add('d-none'));
  });
});
