import React, { Component } from 'react';

export default class Nav extends Component {
    render() {
        return (
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container-fluid">
                    <a class="navbar-brand" href="/">Home</a>
                    <button type="button" class="navbar-toggler collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="navbar-collapse collapse">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item">
                                <a class="nav-link" href="/blog">Blog</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Photography
                                </a>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
                                    <li><a class="dropdown-item" href="/photography">Gallery</a></li>
                                    <li><a class="dropdown-item" href="/photography/usage">Usage Notice</a></li>
                                </ul>
                                </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/smp">SMP</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        );
    }
}

