import { fetchJson, sanitize, authHeaders, isLoggedIn, formatWhen, closeOnOutside } from './lib/util.js';
import { API } from './lib/api.js';
import { createFeedItem } from './lib/feed-render.js';
import { createRootFetcher, canDeleteFor, onLikeAction, onDeleteAction } from './lib/feed-context.js';

/** Extract the username from the /u/{username} path. */
function getUsernameFromPath() {
  const m = window.location.pathname.match(/\/u\/(.+)$/);
  return m ? decodeURIComponent(m[1]) : null;
}


let STATE = { before: null, limit: 20, loading: false, done: false };
let ME = { id: null, role: null };
const fetchRoot = createRootFetcher(fetchJson);

/**
 * Load the user's feed page slice.
 * @param {boolean} initial whether to reset pagination
 */
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
    const items = await fetchJson(`${API.posts.userFeed(username)}?${params}`);
    if (!items || items.length === 0) {
      if (list.children.length === 0) {
        list.innerHTML = '<div class="list-group-item">No posts yet.</div>';
      }
      STATE.done = true;
      STATE.loading = false;
      return;
    }
    for (const p of items) {
      const el = createFeedItem(
        { ...p, username },
        {
          sanitize,
          formatWhen,
          isLoggedIn,
          canDelete: canDeleteFor(ME),
          fetchRoot,
          onLike: onLikeAction(fetchJson, authHeaders),
          onDelete: onDeleteAction(fetchJson, authHeaders),
        }
      );
      list.appendChild(el);
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

/** Wire page once DOM is ready. */
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
/**
 * Public user feed behavior.
 *
 * Responsibilities:
 * - Resolve username from path and render their posts (newest first)
 * - Show parent-context cards for replies
 * - Support liking and (owner/admin) deleting items
 */
    if (nearBottom) loadUserFeed(false);
  });
  closeOnOutside('.post-menu');
});
