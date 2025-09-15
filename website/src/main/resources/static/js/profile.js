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
    item.className = 'list-group-item';
    const when = new Date(p.createdOn || p.lastUpdatedOn || Date.now()).toLocaleString();
    const handle = username ? `@${username.replace(/</g, '&lt;')}` : '@me';
    item.innerHTML = `
      <div class="d-flex w-100 justify-content-between align-items-start">
        <div>
          <div class="fw-semibold">${handle}</div>
          <p class="mb-1">${(p.text || '').replace(/</g, '&lt;')}</p>
        </div>
        <small class="text-muted ms-3 flex-shrink-0">${when}</small>
      </div>
    `;
    container.appendChild(item);
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
});
