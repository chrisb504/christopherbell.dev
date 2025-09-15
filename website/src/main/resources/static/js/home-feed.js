import { authHeaders, fetchJson, sanitize, isLoggedIn, formatWhen, closeOnOutside } from './lib/util.js';
import { API } from './lib/api.js';
import { createFeedItem } from './lib/feed-render.js';
import { createRootFetcher, createThreadFetcher, canDeleteFor, onLikeAction, onDeleteAction, onReplyAction } from './lib/feed-context.js';
import { initComposer } from './lib/composer.js';

/** Update compose character counter. */
function updateCounter(el, counter) {
  const len = (el.value || '').length;
  counter.textContent = `${len} / 280`;
}

/** Enable/disable composer controls. */
function setComposerEnabled(enabled) {
  const postBtn = document.getElementById('postBtn');
  const postText = document.getElementById('postText');
  if (postBtn) postBtn.disabled = !enabled;
  if (postText) postText.disabled = !enabled;
}

let FEED_STATE = { before: null, limit: 20, loading: false, done: false, latest: null };
let USER_STATE = { id: null, role: null, username: null };
const fetchRoot = createRootFetcher(fetchJson);
const fetchThread = createThreadFetcher(fetchJson);

/**
 * Load the global feed page slice.
 * @param {boolean} initial whether to reset pagination
 */
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
    const posts = await fetchJson(`${API.posts.feed}?${params.toString()}`);
    if (!posts || posts.length === 0) {
      if (feedList.children.length === 0) {
        feedList.innerHTML = '<div class="list-group-item">No posts yet.</div>';
      }
      FEED_STATE.done = true;
      FEED_STATE.loading = false;
      return;
    }
    for (const p of posts) {
      const el = createFeedItem(
        p,
        {
          sanitize,
          formatWhen,
          isLoggedIn,
          canDelete: canDeleteFor(USER_STATE),
          fetchRoot,
          fetchThread,
          onLike: onLikeAction(fetchJson, authHeaders),
          onDelete: onDeleteAction(fetchJson, authHeaders),
          onReply: onReplyAction(fetchJson, authHeaders),
          currentUserName: USER_STATE?.username || null,
        }
      );
      feedList.appendChild(el);
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

/** Submit a new top-level post from the composer. */
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
    await fetchJson(API.posts.create, {
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

/** Wire page once DOM is ready. */
document.addEventListener('DOMContentLoaded', async () => {
  // Load current user to determine delete permissions before first render
  const token = localStorage.getItem('cbellLoginToken');
  if (token) {
    try {
      const me = await fetchJson(API.accounts.me, { headers: authHeaders() });
      USER_STATE = { id: me.id, role: me.role, username: me.username };
    } catch (_) { /* ignore */ }
  }
  // Close any open menus on outside click
  closeOnOutside('.post-menu');
  // Composer
  initComposer({
    selectors: {
      composer: '#composer',
      prompt: '#composerPrompt',
      textarea: '#postText',
      counter: '#charCount',
      button: '#postBtn',
      alert: '#homeAlert',
    },
    isLoggedIn,
    onSubmit: async (text) => {
      await fetchJson(API.posts.create, { method: 'POST', headers: authHeaders(), body: JSON.stringify({ text }) });
      await loadFeed(true);
      window.scrollTo({ top: 0, behavior: 'smooth' });
    },
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
/**
 * Global home feed behavior.
 *
 * Responsibilities:
 * - Load newest posts with infinite scroll and 15s polling
 * - Render each item with user handle, content, parent-context (when reply)
 * - Allow posting (when authenticated), liking, and deleting (owner/admin)
 */
      if (!top) return;
      const topTs = top.createdOn || top.lastUpdatedOn;
      if (FEED_STATE.latest && new Date(topTs) > new Date(FEED_STATE.latest)) {
        if (banner) banner.classList.remove('d-none');
      }
    } catch (_) { /* ignore polling failures */ }
  }, 15000);
});
