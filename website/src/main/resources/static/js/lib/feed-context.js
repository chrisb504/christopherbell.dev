/**
 * Small helpers to build feed rendering context functions.
 */
import { API } from './api.js';

/**
 * Create a root post fetcher with simple in-memory caching.
 * @param {(url:string)=>Promise<object>} fetchJson
 * @returns {(rootId:string)=>Promise<object>}
 */
export function createRootFetcher(fetchJson) {
  const cache = {};
  return async function fetchRoot(rootId) {
    if (!cache[rootId]) cache[rootId] = await fetchJson(API.posts.byId(rootId));
    return cache[rootId];
  };
}

/**
 * Build a canDelete predicate for a given current user.
 * @param {{id?:string, role?:string}} currentUser
 * @returns {(post:{accountId?:string})=>boolean}
 */
export function canDeleteFor(currentUser) {
  return function (post) {
    if (!currentUser) return false;
    if (currentUser.role === 'ADMIN') return true;
    return !!currentUser.id && currentUser.id === post.accountId;
  };
}

/**
 * Build an onLike action that posts to the API and returns updated like state.
 * @param {(url:string, options?:object)=>Promise<object>} fetchJson
 * @param {()=>object} authHeaders
 * @returns {(postId:string)=>Promise<{likesCount:number, liked:boolean}>}
 */
export function onLikeAction(fetchJson, authHeaders) {
  return (postId) => fetchJson(API.posts.like(postId), { method: 'POST', headers: authHeaders() });
}

/**
 * Build an onDelete action that deletes the post by id.
 * @param {(url:string, options?:object)=>Promise<object>} fetchJson
 * @param {()=>object} authHeaders
 * @returns {(postId:string)=>Promise<void>}
 */
export function onDeleteAction(fetchJson, authHeaders) {
  return (postId) => fetchJson(API.posts.byId(postId), { method: 'DELETE', headers: authHeaders() });
}

/**
 * Build an onReply action that creates a reply under a given post id.
 * @param {(url:string, options?:object)=>Promise<object>} fetchJson
 * @param {()=>object} authHeaders
 * @returns {(postId:string, text:string)=>Promise<object>}
 */
export function onReplyAction(fetchJson, authHeaders) {
  return (postId, text) => fetchJson(API.posts.create, {
    method: 'POST',
    headers: authHeaders(),
    body: JSON.stringify({ text, parentId: postId })
  });
}
