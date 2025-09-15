import pubsub from '../components/pubsub.js';

const alertBox = () => document.getElementById('loginAlert');

async function login(email, password) {
  const resp = await fetch('/api/accounts/2024-12-15/login', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ email, password })
  });
  const data = await resp.json().catch(() => ({}));
  if (!resp.ok || !data.success) {
    const msg = data?.messages?.[0]?.description || 'Login failed. Please try again.';
    throw new Error(msg);
  }
  return data.payload; // JWT token
}

document.addEventListener('DOMContentLoaded', () => {
  // If already logged in, redirect to home
  if (localStorage.getItem('cbellLoginToken')) {
    window.location.href = '/';
    return;
  }
  const form = document.getElementById('loginForm');
  if (!form) return;

  form.addEventListener('submit', async (e) => {
    e.preventDefault();
    const email = document.getElementById('email')?.value?.trim();
    const password = document.getElementById('password')?.value || '';
    const alert = alertBox();
    alert?.classList.add('d-none');
    try {
      const token = await login(email, password);
      localStorage.setItem('cbellLoginToken', token);
      pubsub.publish('auth:login');
      window.location.href = '/';
    } catch (err) {
      if (alert) {
        alert.textContent = err.message;
        alert.classList.remove('d-none');
      }
    }
  });
});
