function sanitize(text) {
  return (text || '').replace(/</g, '&lt;').replace(/>/g, '&gt;');
}

function getUsernameFromPath() {
  const m = window.location.pathname.match(/\/u\/(.+)$/);
  return m ? decodeURIComponent(m[1]) : null;
}

function authHeaders() {
  const token = localStorage.getItem('cbellLoginToken');
  return token ? { Authorization: `Bearer ${token}` } : {};
}

async function fetchJson(url, options = {}) {
  const resp = await fetch(url, { ...options, headers: { 'Content-Type': 'application/json', ...(options.headers || {}) } });
  const data = await resp.json().catch(() => ({}));
  if (!resp.ok || data.success === false) {
    const msg = data?.messages?.[0]?.description || `Request failed: ${resp.status}`;
    throw new Error(msg);
  }
  return data.payload ?? data;
}

let STATE = { before: null, limit: 20, loading: false, done: false };
let ME = { id: null, role: null };
const ROOT_CACHE = {};

async function loadUserFeed(initial = false) {
  const list = document.getElementById('userFeed');
  const alert = document.getElementById('userAlert');
  const username = getUsernameFromPath();
  if (!list || !username) return;
  if (initial) {
    STATE = { before: null, limit: 20, loading: false, done: false };
    list.innerHTML = '';
    document.getElementById('userHandle').textContent = `@${username}`;
  }
  if (STATE.loading || STATE.done) return;
  STATE.loading = true;
  try {
    const params = new URLSearchParams();
    params.set('limit', STATE.limit.toString());
    if (STATE.before) params.set('before', STATE.before);
    const items = await fetchJson(`/api/posts/2025-09-14/user/${encodeURIComponent(username)}/feed?${params}`);
    if (!items || items.length === 0) {
      if (list.children.length === 0) {
        list.innerHTML = '<div class="list-group-item">No posts yet.</div>';
      }
      STATE.done = true;
      STATE.loading = false;
      return;
    }
    for (const p of items) {
      const when = new Date(p.createdOn || p.lastUpdatedOn || Date.now()).toLocaleString();
      const item = document.createElement('div');
      item.className = 'list-group-item py-3';
      const canDelete = (ME.id && (ME.role === 'ADMIN' || ME.id === p.accountId));
      item.innerHTML = `
        <div class="d-flex w-100 justify-content-between align-items-start">
          <div>
            <div class="fw-semibold">@${sanitize(username)}</div>
            ${p.level && p.level > 0 && p.rootId ? `<div class="border rounded p-2 mb-2 bg-light-subtle small">
                <span class="text-muted">In reply to</span>
                <a href="/p/${encodeURIComponent(p.rootId)}" class="ms-1">view thread</a>
                <div class="mt-1" data-root="${p.rootId}">Loading context…</div>
              </div>` : ''}
            <p class="mb-1 fs-5">${sanitize(p.text)}</p>
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
      `;
      list.appendChild(item);
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
    const last = items[items.length - 1];
    STATE.before = last.createdOn || last.lastUpdatedOn;
    if (items.length < STATE.limit) STATE.done = true;
  } catch (err) {
    if (alert) {
      alert.textContent = err.message;
      alert.classList.remove('d-none');
    }
  }
  STATE.loading = false;
}

document.addEventListener('DOMContentLoaded', async () => {
  // Try to resolve current user to determine delete permissions
  if (localStorage.getItem('cbellLoginToken')) {
    try {
      const me = await fetchJson('/api/accounts/2025-09-03/me', { headers: authHeaders() });
      ME = { id: me.id, role: me.role };
    } catch (_) {}
  }
  loadUserFeed(true);
  window.addEventListener('scroll', () => {
    const nearBottom = window.innerHeight + window.scrollY >= document.body.offsetHeight - 200;
    if (nearBottom) loadUserFeed(false);
  });
  document.addEventListener('click', () => {
    document.querySelectorAll('.post-menu').forEach(m => m.classList.add('d-none'));
  });
});
