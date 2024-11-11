import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import SignUpForm from '../components/forms/signupform.js'

class SignUp extends Component {
  render() {
    return (
      <div>
        <SignUpForm />
      </div>
    );
  }
}

export default SignUp;