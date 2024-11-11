import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Navigate } from 'react-router-dom';
import './search.css';

export default class Search extends Component {
    constructor(props) {
        super(props);
        this.state = {
            accounts: [],
            searchTerm: '',
            error: ''
        };
    }

    handleSubmit = async (event) => {
        event.preventDefault();
        const { searchTerm} = this.state;
        const clientId = 'void-client';
        const url = '/api/search/' + searchTerm;
        const options = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'clientId': clientId
            }
        };

        fetch(url, options)
            .then(response => {
                if (response.status === 200) {
                    const loginToken = response.headers.get('loginToken');
                    localStorage.setItem('voidLoginToken', loginToken);
                    response.json().then(data => {
                        // const accountId = data.myself.id
                        // localStorage.setItem('voidAccountId', accountId);
                        // window.location.href = '/';
                        this.setState({
                            accounts: data.accounts
                        })
                    });
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
        const { searchTerm } = this.state;
        return (
            <div className="search-container">
                <form onSubmit={this.handleSubmit}>
                    <input type="search-input" id="searchTerm" name="searchTerm" value={searchTerm} onChange={this.handleChange} required />
                    <button type="search-button">Search</button>
                </form>
            </div>
        );
    }
}

window.addEventListener('load', () => {
    if (document.body.contains(document.getElementById('search'))) {
        // Used to render the component on the page. The default refresh is when state is changed.
        ReactDOM.render(<Search />, document.getElementById('search'));
    }
});
