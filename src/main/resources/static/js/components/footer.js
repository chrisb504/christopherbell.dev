class AppFooter extends HTMLElement {
    connectedCallback() {
        const year = new Date().getFullYear();
        this.innerHTML = `
<footer class="bg-dark text-light py-3 mt-4">
    <div class="container text-center">
        <span>&copy; ${year} Christopher Bell</span>
    </div>
</footer>`;
    }
}

customElements.define('app-footer', AppFooter);
