import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Navigate } from 'react-router-dom';
import { Link } from 'react-router-dom';
import './crygallery.css';

export default class HomeCryGallery extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isAccountLoggedIn: localStorage.hasOwnProperty('voidLoginToken') && localStorage.hasOwnProperty("voidAccountId"),
            notLoggedInMessage: 'You are not logged in.',
            responseData: null,
            loading: true,
        };
    }

    getCries = async () => {
        const clientId = 'void-client';
        const accountId = localStorage.getItem('voidAccountId');
        const loginToken = localStorage.getItem('voidLoginToken');
        const url = '/api/cries/v1/' + accountId;
        const options = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'clientId': clientId,
                'loginToken': loginToken
            }
        };
        this.setState({ loading: true });

        console.log("Running the fetch!")

        fetch(url, options)
            .then(response => {
                // Check if the response is successful (status code 2xx)
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                // Parse the JSON data from the response
                return response.json();
            }).then(data => {
                console.log(data);
                // Update the component state with the JSON response data
                this.setState({
                    responseData: data,
                    loading: false,
                });

                // Now you can use the 'responseData' variable as needed

            }).catch(error => {
                // Handle any errors that occurred during the fetch
                console.error('Fetch error:', error);
                this.setState({ loading: false });
            });
    };

    handleLocalStorageChange = (event) => {
        this.setState({
            isAccountLoggedIn: localStorage.hasOwnProperty('voidLoginToken') && localStorage.hasOwnProperty("voidAccountId"),
        });
    }

    handleChange = (event) => {
        const { name, value } = event.target;
        this.setState({ [name]: value });
    }

    componentDidMount() {
        const { isAccountLoggedIn } = this.state;
        console.log("Mounting");
        // Add event listener to listen for changes in local storage
        window.addEventListener('storage', this.handleLocalStorageChange);
        // Call the fetch method when the component mounts
        if(isAccountLoggedIn) {
            this.getCries();
        }  
    }

    render() {
        const { isAccountLoggedIn, notLoggedInMessage, responseData, loading } = this.state;
        if(isAccountLoggedIn) {
            return (
                <div className="cry-gallery">
                    {loading ? (<p>Loading...</p>) :
                    responseData.cries.map(cry => (
                        <div className="cry-entry">
                            <div className="cry-author"><Link to={'/profile/' + cry.author}>{cry.author}</Link></div>
                            <div className="cry-text">{cry.text}</div>
                        </div>
                    ))}
                </div>
            );
        }
    }
}

window.addEventListener('load', () => {
    if (document.body.contains(document.getElementById('home-cry-gallery'))) {
        // Used to render the component on the page. The default refresh is when state is changed.
        ReactDOM.render(<HomeCryGallery />, document.getElementById('home-cry-gallery'));
    }
});
