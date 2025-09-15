/**
 * Small, focused utilities used across page scripts (KISS/SOLID).
 *
 * Each function addresses a single concern and can be imported
 * individually without side effects.
 */

/** Whether a login token exists in localStorage. */
export function isLoggedIn() {
  return !!localStorage.getItem('cbellLoginToken');
}

/** Authorization header using the stored token (if present). */
export function authHeaders() {
  const token = localStorage.getItem('cbellLoginToken');
  return token ? { Authorization: `Bearer ${token}` } : {};
}

/**
 * Fetch JSON from an endpoint and return the payload.
 * Throws an Error when HTTP status is not OK or when the
 * API envelope indicates success=false.
 */
export async function fetchJson(url, options = {}) {
  const resp = await fetch(url, {
    ...options,
    headers: {
      'Content-Type': 'application/json',
      ...(options.headers || {}),
    },
  });
  const data = await resp.json().catch(() => ({}));
  if (!resp.ok || data.success === false) {
    const msg = data?.messages?.[0]?.description || `Request failed: ${resp.status}`;
    throw new Error(msg);
  }
  return data.payload ?? data;
}

/** Escape angle brackets for safe HTML text injection. */
export function sanitize(text) {
  return (text || '').replace(/</g, '&lt;').replace(/>/g, '&gt;');
}

/** Convert an ISO datetime (or now) into a localized string. */
export function formatWhen(isoString) {
  return new Date(isoString || Date.now()).toLocaleString();
}

/**
 * Close menus when clicking anywhere outside them.
 * Adds a capture-phase document click listener that hides all elements
 * matching the given selector by adding the 'd-none' class.
 * @param {string} selector CSS selector for menu containers
 */
export function closeOnOutside(selector) {
  document.addEventListener('click', () => {
    document.querySelectorAll(selector).forEach(el => el.classList.add('d-none'));
  }, { capture: true });
}

// --- Local like persistence (for UI consistency when logged out) ---
const LIKE_KEY = 'cbellLikedPosts';

function readLikeSet() {
  try {
    const raw = localStorage.getItem(LIKE_KEY);
    if (!raw) return new Set();
    const arr = JSON.parse(raw);
    if (Array.isArray(arr)) return new Set(arr);
    return new Set();
  } catch { return new Set(); }
}

function writeLikeSet(set) {
  try {
    localStorage.setItem(LIKE_KEY, JSON.stringify(Array.from(set)));
  } catch {}
}

export function isLocallyLiked(postId) {
  if (!postId) return false;
  const set = readLikeSet();
  return set.has(postId);
}

export function setLocallyLiked(postId, liked) {
  if (!postId) return;
  const set = readLikeSet();
  if (liked) set.add(postId); else set.delete(postId);
  writeLikeSet(set);
}
