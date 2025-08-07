import React, { Component } from 'react';

class HomePage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isAccountLoggedIn: localStorage.hasOwnProperty('cbellLoginToken') && localStorage.hasOwnProperty("cbellEmail")
        };
    }

    getGreeting() {
        const hour = new Date().getHours();
        let greeting;
        if (hour < 12) {
            greeting = 'Good morning';
        } else if (hour < 18) {
            greeting = 'Good afternoon';
        } else {
            greeting = 'Good evening';
        }

        if (this.state.isAccountLoggedIn) {
            const email = localStorage.getItem('cbellEmail');
            return `${greeting}, ${email}!`;
        }

        return `${greeting}!`;
    }

    render() {
        return (
            <div id="home-root">
                <div className="jumbotron jumbotron-fluid">
                    <div className="container-fluid">
                        <h1 className="display-3 text-center">{this.getGreeting()}</h1>
                    </div>
                </div>
                <div className="container-fluid bg-light py-5">
                    <div className="card-group">
                        <div className="card mb-4 text-center">
                            <div className="card-body">
                                <h5 className="card-title">Blog</h5>
                                <p className="card-text">Read my latest blog post</p>
                                <a href="/blog" className="btn btn-primary flush-light-blue">Go</a>
                            </div>
                        </div>
                        <div className="w-100 d-none d-sm-block d-md-none">
                        </div>
                        <div className="card mb-4 text-center">
                            <div className="card-body">
                                <h5 className="card-title">What's For Lunch?</h5>
                                <p className="card-text">Find out what to eat in Austin today.</p>
                                <a href="/wfl" className="btn btn-primary flush-light-blue">Go</a>
                            </div>
                        </div>
                        <div className="w-100 d-none d-md-block d-lg-none">
                        </div>
                        <div className="w-100 d-none d-sm-block d-md-none">
                        </div>
                        <div className="w-100 d-none d-lg-block d-xl-none">
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default HomePage;