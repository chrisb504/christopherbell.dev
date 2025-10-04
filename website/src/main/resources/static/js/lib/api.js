/**
 * Centralized API routes (versioned URLs).
 *
 * Keep endpoints here to avoid repetition and make upgrades simple.
 */
export const API = {
  accounts: {
    base: '/api/accounts/2024-12-15',
    login: '/api/accounts/2024-12-15/login',
    create: '/api/accounts/2024-12-15/create',
    me: '/api/accounts/2025-09-03/me',
  },
  posts: {
    base: '/api/posts/2025-09-14',
    feed: '/api/posts/2025-09-14/feed',
    userFeed: (username) => `/api/posts/2025-09-14/user/${encodeURIComponent(username)}/feed`,
    meFeed: '/api/posts/2025-09-14/me/feed',
    create: '/api/posts/2025-09-14/create',
    byId: (id) => `/api/posts/2025-09-14/${encodeURIComponent(id)}`,
    like: (id) => `/api/posts/2025-09-14/${encodeURIComponent(id)}/like`,
    thread: (id) => `/api/posts/2025-09-14/${encodeURIComponent(id)}/thread`,
  },
};

