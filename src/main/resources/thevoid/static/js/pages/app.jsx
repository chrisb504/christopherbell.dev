import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Routes, Route, Link } from "react-router-dom";
import HomePage from './home_page.jsx';
import LoginPage from './login_page.jsx';
import Layout from './layout.jsx';
import NoMatch from "./no-match.jsx";
import SignUpPage from './sign_up_page.jsx';
import ProfilePage from './profile_page.jsx';

class App extends Component {
    render() {
        return (
            <div id="app-root">
                <Routes>
                    <Route path="/" element={<Layout />}>
                        <Route index element={<HomePage />} />
                        <Route path="login" element={<LoginPage />} />
                        <Route path="signup" element={<SignUpPage />} />
                        <Route path="profile*" element={<ProfilePage />} />
                        <Route path="*" element={<NoMatch />} />
                    </Route>
                </Routes>
            </div>
        );
    }
}

export default App;