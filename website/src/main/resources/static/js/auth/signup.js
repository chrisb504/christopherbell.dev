const alertBox = () => document.getElementById('signupAlert');

async function signup(payload) {
  const resp = await fetch('/api/accounts/2024-12-15/create', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload)
  });
  const data = await resp.json().catch(() => ({}));
  if (!resp.ok || !data.success) {
    const msg = data?.messages?.[0]?.description || 'Sign up failed. Please try again.';
    throw new Error(msg);
  }
  return data.payload; // account detail
}

document.addEventListener('DOMContentLoaded', () => {
  // If already logged in, redirect to home
  if (localStorage.getItem('cbellLoginToken')) {
    window.location.href = '/';
    return;
  }
  const form = document.getElementById('signupForm');
  if (!form) return;

  form.addEventListener('submit', async (e) => {
    e.preventDefault();
    const payload = {
      email: document.getElementById('email')?.value?.trim(),
      username: document.getElementById('username')?.value?.trim(),
      firstName: document.getElementById('firstName')?.value?.trim() || null,
      lastName: document.getElementById('lastName')?.value?.trim() || null,
      password: document.getElementById('password')?.value || ''
    };
    const alert = alertBox();
    alert?.classList.add('d-none');
    try {
      await signup(payload);
      window.location.href = '/login';
    } catch (err) {
      if (alert) {
        alert.textContent = err.message;
        alert.classList.remove('d-none');
      }
    }
  });
});
