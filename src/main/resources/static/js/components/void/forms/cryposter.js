import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import './cryposter.css';

// Class responsible for taking a new cry from a user and submitting it to the DB.

export default class CryPoster extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isAccountLoggedIn: localStorage.hasOwnProperty('voidLoginToken') && localStorage.hasOwnProperty("voidAccountId"),
            notLoggedInMessage: 'You are not logged in.',
            cryText: '',
            error: '',
            showPopup: false,
            popupMessage: ''
        };
    }

    handleSubmit = async (event) => {
        event.preventDefault();
        const { cryText } = this.state;
        const accountId = localStorage.getItem('voidAccountId');
        const loginToken = localStorage.getItem('voidLoginToken');
        const clientId = 'void-client';
        const url = '/api/cries/v1/create/account/' + accountId;
        const cry = { "text": cryText, "creationDate": new Date(), "expirationDate": new Date(new Date().getTime() + 60 * 60 * 24 * 1000) }
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'clientId': clientId,
                'loginToken': loginToken
            },
            body: JSON.stringify({ cry })
        };

        fetch(url, options)
            .then(response => {
                if (response.status === 200) {
                    response.json().then(data => {
                        window.location.href = '/';
                    });
                } else {
                }
            })
            .catch(error => console.log(error));
    };

    handleChange = (event) => {
        const { name, value } = event.target;
        this.setState({ [name]: value });
    }

    render() {
        const { cryText, isAccountLoggedIn } = this.state;
        if (isAccountLoggedIn) {
            return (
                <div className="cry-poster-container">
                    <form onSubmit={this.handleSubmit}>
                        <label htmlFor="Cry">Cry:</label>
                        <input type="cryText" id="cryText" name="cryText" value={cryText} onChange={this.handleChange} required />
                        <button type="submit">Send</button>
                    </form>
                </div>
            );
        }
    }
}

window.addEventListener('load', () => {
    if (document.body.contains(document.getElementById('cryPoster'))) {
        // Used to render the component on the page. The default refresh is when state is changed.
        ReactDOM.render(<CryPoster />, document.getElementById('cryPoster'));
    }
});