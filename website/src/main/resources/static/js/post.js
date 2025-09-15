function sanitize(text) { return (text || '').replace(/</g, '&lt;').replace(/>/g, '&gt;'); }
function authHeaders() { const t = localStorage.getItem('cbellLoginToken'); return t ? { Authorization: `Bearer ${t}` } : {}; }
function getPostId() { const m = location.pathname.match(/\/p\/(.+)$/); return m ? decodeURIComponent(m[1]) : null; }

async function fetchJson(url, options = {}) {
  const resp = await fetch(url, { ...options, headers: { 'Content-Type': 'application/json', ...(options.headers || {}) } });
  const data = await resp.json().catch(() => ({}));
  if (!resp.ok || data.success === false) {
    const msg = data?.messages?.[0]?.description || `Request failed: ${resp.status}`;
    throw new Error(msg);
  }
  return data.payload ?? data;
}

function renderRoot(post) {
  const root = document.querySelector('#rootPost .card-body');
  if (!root) return;
  const when = new Date(post.createdOn || post.lastUpdatedOn || Date.now()).toLocaleString();
  root.innerHTML = `
    <div class="d-flex justify-content-between align-items-start">
      <div>
        <div class="fw-semibold"><a href="/u/${encodeURIComponent(post.username)}" class="link-underline link-underline-opacity-0">@${sanitize(post.username)}</a></div>
        <p class="mb-1 fs-4">${sanitize(post.text)}</p>
      </div>
      <small class="text-muted ms-3 flex-shrink-0">${when}</small>
    </div>
  `;
}

function renderThread(items, currentUser) {
  const list = document.getElementById('threadList');
  if (!list) return;
  list.innerHTML = '';
  const isAdmin = currentUser?.role === 'ADMIN';
  for (const p of items) {
    if (p.level === 0) continue; // skip root in replies
    const canDelete = currentUser && (isAdmin || currentUser.id === p.accountId);
    const when = new Date(p.createdOn || p.lastUpdatedOn || Date.now()).toLocaleString();
    const item = document.createElement('div');
    item.className = 'list-group-item py-3';
    item.innerHTML = `
      <div class="d-flex w-100 justify-content-between align-items-start">
        <div>
          <div class="fw-semibold"><a href="/u/${encodeURIComponent(p.username)}" class="link-underline link-underline-opacity-0">@${sanitize(p.username)}</a></div>
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
        if (!confirm('Delete this reply?')) return;
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
  document.addEventListener('click', () => {
    document.querySelectorAll('.post-menu').forEach(m => m.classList.add('d-none'));
  });
  const id = getPostId();
  if (!id) return;
  const alert = document.getElementById('postAlert');
  try {
    const [post, thread] = await Promise.all([
      fetchJson(`/api/posts/2025-09-14/${encodeURIComponent(id)}`),
      fetchJson(`/api/posts/2025-09-14/${encodeURIComponent(id)}/thread`)
    ]);
    renderRoot(post);
    let me = null;
    if (localStorage.getItem('cbellLoginToken')) {
      try { me = await fetchJson('/api/accounts/2025-09-03/me', { headers: authHeaders() }); } catch (_) {}
    }
    renderThread(thread, me);
    // Show reply composer if logged in
    const composer = document.getElementById('replyComposer');
    const replyBtn = document.getElementById('replyBtn');
    if (me && composer && replyBtn) {
      composer.classList.remove('d-none');
      replyBtn.addEventListener('click', async () => {
        const txtEl = document.getElementById('replyText');
        const text = (txtEl?.value || '').trim();
        if (!text) return;
        try {
          await fetchJson('/api/posts/2025-09-14/create', {
            method: 'POST',
            headers: authHeaders(),
            body: JSON.stringify({ text, parentId: id })
          });
          txtEl.value = '';
          const thread = await fetchJson(`/api/posts/2025-09-14/${encodeURIComponent(id)}/thread`);
          renderThread(thread, me);
        } catch (err) {
          if (alert) { alert.textContent = err.message; alert.classList.remove('d-none'); }
        }
      });
    }
  } catch (err) {
    if (alert) { alert.textContent = err.message; alert.classList.remove('d-none'); }
  }
});

