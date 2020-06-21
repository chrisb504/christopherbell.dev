import React, { Component } from 'react';

export default class Nav extends Component {
    render() {
        return (
            <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark" role="navigation">
                <a class="navbar-brand" href="/">Azurras</a>
                <button type="button" class="navbar-toggler collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="navbar-collapse collapse">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="/blog">Blog</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/resume">Resume</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/whatsforlunch">What's For Lunch?</a>
                        </li>
                    </ul>
                </div>
            </nav>
        );
    } 
}

