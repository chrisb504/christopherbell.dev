import { sanitize, authHeaders, fetchJson, isLoggedIn, formatWhen, closeOnOutside } from './lib/util.js';
import { API } from './lib/api.js';
/** Extract the post id from the /p/{id} path. */
function getPostId() { const m = location.pathname.match(/\/p\/(.+)$/); return m ? decodeURIComponent(m[1]) : null; }


/**
 * Render the root post header.
 * @param {{username:string,text:string,createdOn?:string,lastUpdatedOn?:string}} post
 */
function renderRoot(post) {
  const root = document.querySelector('#rootPost .card-body');
  if (!root) return;
  const when = formatWhen(post.createdOn || post.lastUpdatedOn);
  const liked = !!post.liked;
  const likes = post.likesCount || 0;
  const replies = post.replyCount || 0;
  root.innerHTML = `
    ${(post.rootId && post.rootId !== post.id && post.rootId !== post.parentId) ? `
    <div class="root-context card mb-2" data-root="${post.rootId}">
      <div class="card-body py-2">
        <div class="small text-muted">Thread root</div>
        <div class="fw-semibold">
          <a href="/u/" class="link-underline link-underline-opacity-0" data-root-handle="${post.rootId}">@user</a>
        </div>
        <p class="mb-0 small fw-semibold" data-root-text="${post.rootId}">Loading…</p>
        <a href="/p/${encodeURIComponent(post.rootId)}" class="small">View root</a>
      </div>
    </div>` : ''}
    ${post.parentId ? `
    <div class="parent-context card mb-2" data-parent="${post.parentId}">
      <div class="card-body py-2">
        <div class="small text-muted">In reply to</div>
        <div class="fw-semibold">
          <a href="/u/" class="link-underline link-underline-opacity-0" data-parent-handle="${post.parentId}">@user</a>
        </div>
        <p class="mb-0 small fw-semibold" data-parent-text="${post.parentId}">Loading…</p>
        <a href="/p/${encodeURIComponent(post.parentId)}" class="small">View parent</a>
      </div>
    </div>` : ''}
    <div class="d-flex justify-content-between align-items-start">
      <div class="w-100">
        <div class="fw-semibold"><a href="/u/${encodeURIComponent(post.username)}" class="link-underline link-underline-opacity-0">@${sanitize(post.username)}</a></div>
        <p class="mb-1 fs-4"><a href="/p/${encodeURIComponent(post.id)}" class="link-underline link-underline-opacity-0 text-body">${sanitize(post.text)}</a></p>
      </div>
      <small class="text-muted ms-3 flex-shrink-0">${when}</small>
    </div>
    <div class="d-flex align-items-center gap-4 border-top pt-2 mt-2">
      <button class="btn btn-link btn-sm text-decoration-none text-muted root-reply-btn" aria-label="Reply">
        <i class="fa fa-comment-o" aria-hidden="true"></i>
        <span class="reply-count ms-1">${replies}</span>
        <span class="visually-hidden">Reply</span>
      </button>
      <button class="btn btn-link btn-sm text-decoration-none root-like-btn ${liked ? 'text-danger' : 'text-muted'}" data-liked="${liked}" aria-label="Like">
        <i class="fa ${liked ? 'fa-heart' : 'fa-heart-o'}" aria-hidden="true"></i>
        <span class="like-count ms-1">${likes}</span>
      </button>
    </div>
    <div class="root-reply-composer d-none mt-2">
      <textarea class="form-control root-reply-text" rows="2" maxlength="280" placeholder="Write a reply..."></textarea>
      <div class="d-flex justify-content-end gap-2 mt-2">
        <button class="btn btn-sm btn-secondary root-reply-cancel" type="button">Cancel</button>
        <button class="btn btn-sm btn-primary root-reply-submit" type="button">Reply</button>
      </div>
    </div>
  `;

  // Wire like toggle for root
  const likeBtn = root.querySelector('.root-like-btn');
  if (likeBtn) {
    likeBtn.addEventListener('click', async () => {
      if (!isLoggedIn()) { window.location.href = '/login'; return; }
      try {
        const updated = await fetchJson(`/api/posts/2025-09-14/${post.id}/like`, { method: 'POST', headers: authHeaders() });
        const countEl = likeBtn.querySelector('.like-count');
        if (countEl) countEl.textContent = updated.likesCount ?? 0;
        const isLiked = !!updated.liked;
        likeBtn.dataset.liked = isLiked;
        likeBtn.classList.toggle('text-danger', isLiked);
        likeBtn.classList.toggle('text-muted', !isLiked);
        const icon = likeBtn.querySelector('i');
        if (icon) {
          icon.classList.toggle('fa-heart', isLiked);
          icon.classList.toggle('fa-heart-o', !isLiked);
        }
        // Server is source of truth for liked state
      } catch (err) {
        alert(err.message);
      }
    });
  }

  // Wire inline reply for root
  const replyBtn = root.querySelector('.root-reply-btn');
  const replyBox = root.querySelector('.root-reply-composer');
  const replyText = root.querySelector('.root-reply-text');
  const replySubmit = root.querySelector('.root-reply-submit');
  const replyCancel = root.querySelector('.root-reply-cancel');
  if (replyBtn && replyBox && replyText && replySubmit && replyCancel) {
    replyBtn.addEventListener('click', () => {
      if (!isLoggedIn()) { window.location.href = '/login'; return; }
      replyBox.classList.toggle('d-none');
      if (!replyBox.classList.contains('d-none')) setTimeout(() => replyText.focus(), 0);
    });
    replyCancel.addEventListener('click', () => {
      replyText.value = '';
      replyBox.classList.add('d-none');
    });
    replySubmit.addEventListener('click', async () => {
      if (!isLoggedIn()) { window.location.href = '/login'; return; }
      const text = (replyText.value || '').trim();
      if (!text) return;
      const alert = document.getElementById('postAlert');
      try {
        replySubmit.disabled = true;
        await fetchJson('/api/posts/2025-09-14/create', {
          method: 'POST',
          headers: authHeaders(),
          body: JSON.stringify({ text, parentId: post.id })
        });
        // Update UI: clear composer, increment count, refresh replies list
        replyText.value = '';
        replyBox.classList.add('d-none');
        const rc = root.querySelector('.reply-count');
        if (rc) {
          const curr = parseInt(rc.textContent || '0', 10) || 0;
          rc.textContent = String(curr + 1);
        }
        const thread = await fetchJson(`/api/posts/2025-09-14/${encodeURIComponent(post.id)}/thread`);
        // Resolve current user for delete/like permissions
        let me = null;
        if (localStorage.getItem('cbellLoginToken')) {
          try { me = await fetchJson('/api/accounts/2025-09-03/me', { headers: authHeaders() }); } catch (_) {}
        }
        renderThread(thread, me, post.id);
        // Toast
        const toast = document.createElement('div');
        toast.className = 'small text-success mt-1';
        toast.textContent = 'Reply posted';
        root.appendChild(toast);
        setTimeout(() => toast.remove(), 2000);
      } catch (err) {
        if (alert) { alert.textContent = err.message; alert.classList.remove('d-none'); }
        else { alert(err.message); }
      } finally {
        replySubmit.disabled = false;
      }
    });
  }

  // Fill parent context details if this is a reply
  if (post.parentId) {
    (async () => {
      try {
        const parent = await fetchJson(API.posts.byId(post.parentId), { headers: authHeaders() });
        const h = parent.username ? `@${sanitize(parent.username)}` : '@user';
        const handleEl = root.querySelector(`[data-parent-handle="${post.parentId}"]`);
        const textEl = root.querySelector(`[data-parent-text="${post.parentId}"]`);
        if (handleEl) {
          handleEl.textContent = h;
          handleEl.setAttribute('href', `/u/${encodeURIComponent(parent.username || '')}`);
        }
        if (textEl) {
          textEl.textContent = parent.text || '';
        }
      } catch (e) {
        const textEl = root.querySelector(`[data-parent-text="${post.parentId}"]`);
        if (textEl) textEl.textContent = 'Context unavailable';
      }
    })();
  }

  // Fill root context if different from parent
  if (post.rootId && post.rootId !== post.id && post.rootId !== post.parentId) {
    (async () => {
      try {
        const r = await fetchJson(API.posts.byId(post.rootId), { headers: authHeaders() });
        const h = r.username ? `@${sanitize(r.username)}` : '@user';
        const handleEl = root.querySelector(`[data-root-handle="${post.rootId}"]`);
        const textEl = root.querySelector(`[data-root-text="${post.rootId}"]`);
        if (handleEl) {
          handleEl.textContent = h;
          handleEl.setAttribute('href', `/u/${encodeURIComponent(r.username || '')}`);
        }
        if (textEl) {
          textEl.textContent = r.text || '';
        }
      } catch (e) {
        const textEl = root.querySelector(`[data-root-text="${post.rootId}"]`);
        if (textEl) textEl.textContent = 'Context unavailable';
      }
    })();
  }
}

/**
 * Render the replies list, excluding the currentId item if present.
 * @param {Array} items thread feed items
 * @param {{id?:string,role?:string}} currentUser current viewer (optional)
 * @param {string} currentId post id to omit from replies
 */
function renderThread(items, currentUser, currentId) {
  const list = document.getElementById('threadList');
  if (!list) return;
  list.innerHTML = '';
  const isAdmin = currentUser?.role === 'ADMIN';
  // Only render direct replies to the current post id
  const directReplies = (items || []).filter(p => p.parentId === currentId);
  for (const p of directReplies) {
    const canDelete = currentUser && (isAdmin || currentUser.id === p.accountId);
    const when = new Date(p.createdOn || p.lastUpdatedOn || Date.now()).toLocaleString();
    const likes = p.likesCount || 0;
    const liked = !!p.liked;
    const replies = p.replyCount || 0;
    const item = document.createElement('div');
    item.className = 'list-group-item py-3';
    item.innerHTML = `
      <div class="d-flex w-100 justify-content-between align-items-start">
        <div class="w-100">
          <div class="fw-semibold"><a href="/u/${encodeURIComponent(p.username)}" class="link-underline link-underline-opacity-0">@${sanitize(p.username)}</a></div>
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
      <div class="d-flex align-items-center gap-4 border-top pt-2 mt-2">
        <button class="btn btn-link btn-sm text-decoration-none text-muted reply-btn" data-post="${p.id}" aria-label="Reply">
          <i class="fa fa-comment-o" aria-hidden="true"></i>
          <span class="reply-count ms-1">${replies}</span>
          <span class="visually-hidden">Reply</span>
        </button>
        <button class="btn btn-link btn-sm text-decoration-none like-btn ${liked ? 'text-danger' : 'text-muted'}" data-post="${p.id}" data-liked="${liked}" aria-label="Like">
          <i class="fa ${liked ? 'fa-heart' : 'fa-heart-o'}" aria-hidden="true"></i>
          <span class="like-count ms-1">${likes}</span>
        </button>
        <button class="btn btn-link btn-sm text-decoration-none text-muted post-replies-toggle" data-post="${p.id}" aria-expanded="false">
          <i class="fa fa-comments-o" aria-hidden="true"></i>
          <span class="toggle-label">Show replies</span>
        </button>
      </div>
      <div class="reply-composer d-none mt-2">
        <textarea class="form-control reply-text" rows="2" maxlength="280" placeholder="Write a reply..."></textarea>
        <div class="d-flex justify-content-end gap-2 mt-2">
          <button class="btn btn-sm btn-secondary reply-cancel" type="button">Cancel</button>
          <button class="btn btn-sm btn-primary reply-submit" type="button">Reply</button>
        </div>
      </div>
      <div class="replies d-none"></div>
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
    // Like toggle
    const likeBtn = item.querySelector('.like-btn');
    if (likeBtn) {
      likeBtn.addEventListener('click', async () => {
        if (!isLoggedIn()) { window.location.href = '/login'; return; }
        try {
          const updated = await fetchJson(`/api/posts/2025-09-14/${p.id}/like`, { method: 'POST', headers: authHeaders() });
          const countEl = likeBtn.querySelector('.like-count');
          if (countEl) countEl.textContent = updated.likesCount ?? 0;
          const isLiked = !!updated.liked;
          likeBtn.dataset.liked = isLiked;
          likeBtn.classList.toggle('text-danger', isLiked);
          likeBtn.classList.toggle('text-muted', !isLiked);
          const icon = likeBtn.querySelector('i');
          if (icon) {
            icon.classList.toggle('fa-heart', isLiked);
            icon.classList.toggle('fa-heart-o', !isLiked);
          }
          // Server is source of truth for liked state
        } catch (err) {
          alert(err.message);
        }
      });
    }
    // Inline reply composer
    const replyBtn = item.querySelector('.reply-btn');
    const replyBox = item.querySelector('.reply-composer');
    const replyText = item.querySelector('.reply-text');
    const replySubmit = item.querySelector('.reply-submit');
    const replyCancel = item.querySelector('.reply-cancel');
    if (replyBtn && replyBox && replyText && replySubmit && replyCancel) {
      replyBtn.addEventListener('click', () => {
        if (!isLoggedIn()) { window.location.href = '/login'; return; }
        replyBox.classList.toggle('d-none');
        if (!replyBox.classList.contains('d-none')) {
          setTimeout(() => replyText.focus(), 0);
        }
      });
      replyCancel.addEventListener('click', () => {
        replyText.value = '';
        replyBox.classList.add('d-none');
      });
      replySubmit.addEventListener('click', async () => {
        if (!isLoggedIn()) { window.location.href = '/login'; return; }
        const text = (replyText.value || '').trim();
        if (!text) return;
        try {
          replySubmit.disabled = true;
          await fetchJson('/api/posts/2025-09-14/create', {
            method: 'POST',
            headers: authHeaders(),
            body: JSON.stringify({ text, parentId: p.id })
          });
          replyText.value = '';
          replyBox.classList.add('d-none');
          const rc = item.querySelector('.reply-count');
          if (rc) {
            const curr = parseInt(rc.textContent || '0', 10) || 0;
            rc.textContent = String(curr + 1);
          }
          const repliesEl = item.querySelector('.replies');
          if (repliesEl) {
            repliesEl.classList.remove('d-none');
            const who = currentUser?.username ? `@${sanitize(currentUser.username)}` : 'You';
            const whenStr = formatWhen(new Date().toISOString());
            const row = document.createElement('div');
            row.className = 'mt-2';
            row.innerHTML = `
              <div class="d-flex">
                <div class="flex-grow-1">
                  <div class="small text-muted"><span class="fw-semibold">${who}</span> · ${whenStr}</div>
                  <div>${sanitize(text)}</div>
                </div>
              </div>`;
            repliesEl.appendChild(row);
          }
          const toast = document.createElement('div');
          toast.className = 'small text-success mt-1';
          toast.textContent = 'Reply posted';
          replyBox.parentElement?.insertBefore(toast, replyBox.nextSibling);
          setTimeout(() => toast.remove(), 2000);
        } catch (err) {
          alert(err.message);
        } finally {
          replySubmit.disabled = false;
        }
      });
    }

    // Show/hide replies (direct children of this reply)
    const replToggle = item.querySelector('.post-replies-toggle');
    const repliesEl = item.querySelector('.replies');
    if (replToggle && repliesEl) {
      replToggle.addEventListener('click', async () => {
        const expanded = replToggle.getAttribute('aria-expanded') === 'true';
        const label = replToggle.querySelector('.toggle-label');
        if (!expanded) {
          if (!repliesEl.dataset.loaded) {
            try {
              const thread = await fetchJson(API.posts.thread(p.id));
              const direct = (thread || []).filter(r => r.parentId === p.id);
              repliesEl.innerHTML = '';
              if (direct.length === 0) {
                const empty = document.createElement('div');
                empty.className = 'text-muted small mt-1';
                empty.textContent = 'No replies yet';
                repliesEl.appendChild(empty);
              } else {
                for (const r of direct) {
                  const row = document.createElement('div');
                  row.className = 'mt-2';
                  row.innerHTML = `
                    <div class="d-flex">
                      <div class="flex-grow-1">
                        <div class="small text-muted"><a href="/u/${encodeURIComponent(r.username || '')}" class="link-underline link-underline-opacity-0 fw-semibold">@${sanitize(r.username || 'user')}</a> · ${formatWhen(r.createdOn || r.lastUpdatedOn)}</div>
                        <div><a href="/p/${encodeURIComponent(r.id)}" class="link-underline link-underline-opacity-0 text-body">${sanitize(r.text || '')}</a></div>
                      </div>
                    </div>`;
                  repliesEl.appendChild(row);
                }
                const more = document.createElement('div');
                more.className = 'mt-2';
                more.innerHTML = `<a href="/p/${encodeURIComponent(p.id)}" class="small">View full thread</a>`;
                repliesEl.appendChild(more);
              }
              repliesEl.dataset.loaded = 'true';
            } catch (err) {
              repliesEl.innerHTML = `<div class="text-danger small mt-1">${sanitize(err.message)}</div>`;
              repliesEl.dataset.loaded = 'true';
            }
          }
          repliesEl.classList.remove('d-none');
          replToggle.setAttribute('aria-expanded', 'true');
          if (label) label.textContent = 'Hide replies';
        } else {
          repliesEl.classList.add('d-none');
          replToggle.setAttribute('aria-expanded', 'false');
          if (label) label.textContent = 'Show replies';
        }
      });
    }
  }
}

/** Wire page once DOM is ready. */
document.addEventListener('DOMContentLoaded', async () => {
  closeOnOutside('.post-menu');
  const id = getPostId();
  if (!id) return;
  const alert = document.getElementById('postAlert');
  try {
    const [post, thread] = await Promise.all([
      fetchJson(API.posts.byId(id), { headers: authHeaders() }),
      fetchJson(API.posts.thread(id), { headers: authHeaders() })
    ]);
    renderRoot(post);
    let me = null;
    if (localStorage.getItem('cbellLoginToken')) {
      try { me = await fetchJson(API.accounts.me, { headers: authHeaders() }); } catch (_) {}
    }
    renderThread(thread, me, id);
    // The inline reply composer is available under the root; no need to show the top card.
    // If desired, we could still expose #replyComposer; keeping it hidden for a cleaner UI.
  } catch (err) {
    if (alert) { alert.textContent = err.message; alert.classList.remove('d-none'); }
  }
});
