import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Navigate } from 'react-router-dom';
import './signupform.css';

export default class SignUpForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            username: '',
            password: '',
            error: '',
            showPopup: false,
            popupMessage: ''
        };
    }

    handleSubmit = async (event) => {
        event.preventDefault();
        const { email, username, password } = this.state;
        const clientId = 'void-client';
        const url = '/api/accounts/v1/create';
        const account = {
            "username": username,
            "accountSecurity": {
                "email": email,
                "password": password
            }
        };
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'clientId': clientId
            },
            body: JSON.stringify({ account })
        };
        fetch(url, options)
            .then(response => {
                if (response.status === 201) {
                    //const loginToken = response.headers.get('loginToken');
                    //localStorage.setItem('loginToken', loginToken);
                    window.location.href = '/';
                } else {
                    this.setState({
                        showPopup: true,
                        popupMessage: 'The login information given is incorrect.'
                    });
                }
            })
            .catch(error => console.log(error));
    };

    handleChange = (event) => {
        const { name, value } = event.target;
        this.setState({ [name]: value });
    }

    handlePopupClose = () => {
        this.setState({ showPopup: false });
    }

    render() {
        const { email, username, password, showPopup, popupMessage } = this.state;
        return (
            <div className="login-form-container">
                <form onSubmit={this.handleSubmit}>
                    <label htmlFor="email">Email:</label>
                    <input type="email" id="email" name="email" value={email} onChange={this.handleChange} required />
                    <label htmlFor="username">Username:</label>
                    <input type="username" id="username" name="username" value={username} onChange={this.handleChange} required />
                    <label htmlFor="password">Password:</label>
                    <input type="password" id="password" name="password" value={password} onChange={this.handleChange} required />
                    <button type="submit">Create Account</button>
                </form>
                {showPopup && (
                    <div className="popup">
                        <p>{popupMessage}</p>
                        <button onClick={this.handlePopupClose}>Close</button>
                    </div>
                )}
            </div>
        );
    }
}

window.addEventListener('load', () => {
    if (document.body.contains(document.getElementById('signupform'))) {
        // Used to render the component on the page. The default refresh is when state is changed.
        ReactDOM.render(<SignUpForm />, document.getElementById('signupform'));
    }
});