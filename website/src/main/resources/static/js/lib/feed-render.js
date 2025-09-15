/**
 * Create a DOM element representing a post in a feed.
 *
 * The caller supplies minimal callbacks to keep this module focused on
 * rendering and simple event wiring.
 *
 * @param {object} post feed item ({ id, username, accountId, text, createdOn, lastUpdatedOn, level, rootId, likesCount, liked })
 * @param {object} ctx  context with small helpers:
 *  - sanitize(text): string
 *  - formatWhen(iso): string
 *  - isLoggedIn(): boolean
 *  - canDelete(post): boolean
 *  - onLike(postId): Promise<{likesCount:number, liked:boolean}>
 *  - onDelete(postId): Promise<void>
 *  - fetchRoot(rootId): Promise<{username:string, text:string}>
 * @returns {HTMLElement}
 */
export function createFeedItem(post, ctx) {
  const s = ctx.sanitize;
  const when = ctx.formatWhen(post.createdOn || post.lastUpdatedOn);
  const handle = post.username ? `@${s(post.username)}` : '@user';
  const liked = !!post.liked;
  const likes = post.likesCount || 0;

  const item = document.createElement('div');
  item.className = 'list-group-item py-3';
  item.innerHTML = `
    <div class="d-flex w-100 justify-content-between align-items-start">
      <div class="w-100">
        <div class="fw-semibold"><a href="/u/${encodeURIComponent(post.username || '')}" class="link-underline link-underline-opacity-0">${handle}</a></div>
        <p class="mb-1 fs-5"><a href="/p/${encodeURIComponent(post.id)}" class="link-underline link-underline-opacity-0 text-body">${s(post.text)}</a></p>
      </div>
      <div class="ms-3 text-end flex-shrink-0 position-relative">
        <small class="text-muted d-block">${when}</small>
        ${ctx.canDelete(post) ? `
        <button class="btn btn-sm btn-light post-menu-btn mt-1" data-post="${post.id}" aria-label="More">⋯</button>
        <div class="post-menu d-none card p-2" style="position:absolute; right:0; top:100%; z-index:1000;">
          <button class="btn btn-link text-danger p-0 post-delete-btn" data-post="${post.id}">Delete</button>
        </div>` : ''}
      </div>
    </div>
    ${post.level && post.level > 0 && post.rootId ? `<div class="parent-context card mt-2 w-100">
        <div class="card-body py-2">
          <div class="fw-semibold"><a href="/u/" class="link-underline link-underline-opacity-0" data-root-handle="${post.rootId}">@user</a></div>
          <p class="mb-0 fs-4 fw-semibold" data-root="${post.rootId}">Loading…</p>
          <a href="/p/${encodeURIComponent(post.rootId)}" class="small">View thread</a>
        </div>
      </div>` : ''}
    <div class="d-flex align-items-center gap-4 border-top pt-2 mt-2">
      <button class="btn btn-link btn-sm text-decoration-none text-muted post-reply-btn" data-post="${post.id}" aria-label="Reply">
        <i class="fa fa-comment-o" aria-hidden="true"></i>
        <span class="visually-hidden">Reply</span>
      </button>
      <button class="btn btn-link btn-sm text-decoration-none post-like-btn ${liked ? 'text-danger' : 'text-muted'}" data-post="${post.id}" data-liked="${liked}" aria-label="Like">
        <i class="fa ${liked ? 'fa-heart' : 'fa-heart-o'}" aria-hidden="true"></i>
        <span class="like-count ms-1">${likes}</span>
      </button>
    </div>
  `;

  // Wire like toggle
  const likeBtn = item.querySelector('.post-like-btn');
  if (likeBtn) {
    likeBtn.addEventListener('click', async () => {
      if (!ctx.isLoggedIn()) { window.location.href = '/login'; return; }
      try {
        const updated = await ctx.onLike(post.id);
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
      } catch (err) {
        alert(err.message);
      }
    });
  }

  // Wire reply click -> navigate to post page
  const replyBtn = item.querySelector('.post-reply-btn');
  if (replyBtn) {
    replyBtn.addEventListener('click', () => {
      window.location.href = `/p/${encodeURIComponent(post.id)}`;
    });
  }

  // Wire menu toggle and delete
  const menuBtn = item.querySelector('.post-menu-btn');
  const menu = item.querySelector('.post-menu');
  if (menuBtn && menu) {
    menuBtn.addEventListener('click', (e) => {
      e.stopPropagation();
      menu.classList.toggle('d-none');
    });
    const del = item.querySelector('.post-delete-btn');
    del?.addEventListener('click', async (e) => {
      e.stopPropagation();
      menu.classList.add('d-none');
      if (!confirm('Delete this post?')) return;
      try {
        await ctx.onDelete(post.id);
        item.remove();
      } catch (err) {
        alert(err.message);
      }
    });
  }

  // Fill parent context
  if (post.level && post.level > 0 && post.rootId) {
    const ctxEl = item.querySelector(`[data-root="${post.rootId}"]`);
    const handleEl = item.querySelector(`[data-root-handle="${post.rootId}"]`);
    if (ctxEl) {
      (async () => {
        try {
          const root = await ctx.fetchRoot(post.rootId);
          const h = root.username ? `@${s(root.username)}` : '@user';
          if (handleEl) {
            handleEl.textContent = h;
            handleEl.setAttribute('href', `/u/${encodeURIComponent(root.username || '')}`);
          }
          ctxEl.textContent = root.text ? `${root.text}` : '';
        } catch (e) {
          ctxEl.textContent = 'Context unavailable';
        }
      })();
    }
  }

  return item;
}
