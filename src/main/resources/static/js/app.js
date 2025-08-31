import './components/nav.js';
import './components/footer.js';
import './components/blog.js';
import './components/gallery.js';
import pubsub from './components/pubsub.js';

document.addEventListener('DOMContentLoaded', () => {
    const navContainer = document.getElementById('nav');
    if (navContainer) {
        navContainer.appendChild(document.createElement('app-nav'));
    }

    const footerContainer = document.getElementById('footer');
    if (footerContainer) {
        footerContainer.appendChild(document.createElement('app-footer'));
    }

    const blogContainer = document.getElementById('blog');
    if (blogContainer) {
        blogContainer.appendChild(document.createElement('blog-posts'));
    }

    const galleryContainer = document.getElementById('gallery');
    if (galleryContainer) {
        galleryContainer.appendChild(document.createElement('photo-gallery'));
    }

    pubsub.subscribe('auth:logout', () => {
        localStorage.removeItem('cbellLoginToken');
    });
});
