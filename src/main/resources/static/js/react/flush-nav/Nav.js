import React, { Component } from 'react';

export default class Nav extends Component {
    render() {
        return (
            <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
                <a class="navbar-brand" href="/flush">Flush Media</a>
                <button type="button" class="navbar-toggler collapsed" data-toggle="collapse" data-target=".navbar-collapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="navbar-collapse collapse">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="/flush/blog">Blog</a>
                        </li>
                    </ul>
                </div>
            </nav>
        );
    } 
}

