import React, { Component } from 'react';

export default class Footer extends Component {
    render() {
        return (
            <div class="container">
                <div class="row mt-4 ml-1 mr-1">
                    <div class="col text-center">
                    <a class="podcast-link ml-3 mr-3" target="_blank" href="https://podcasts.apple.com/us/podcast/flush/id1516189054">Apple Podcast</a>
                    <a class="podcast-link ml-3 mr-3" target="_blank" href="https://podcasts.google.com/?feed=aHR0cDovL3d3dy5henVycmFzLmNvbS9mbHVzaC9mZWVkLnhtbA&ved=2ahUKEwjmgoiG4ujpAhXMXs0KHbrdAr8Q4aUDegQIARAC&hl=en">Google Podcast</a>
                    <a class="podcast-link ml-3 mr-3" target="_blank" href="https://open.spotify.com/show/6VlgSSlJiRDxFdYeiL3iO5">Spotify Podcast</a>
                    <a class="podcast-link ml-3 mr-3" target="_blank" href="https://discord.gg/xR2FG3U">Join our Discord!</a>
                    </div>
                </div>
            </div>
        );
    } 
}
