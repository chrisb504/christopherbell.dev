import React, { Component } from 'react';

export default class Footer extends Component {
    render() {
        return (
            <div class="container text-center">
                <hr/>
                <div class="row">
                    <div class="col">
                        <p class="mb-n1 font-weight-bold">Christopher Bell</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <a href="mailto:cbell7@icloud.com" target="_blank">cbell7@icloud.com</a>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <a class="mr-3 ml-3" href="https://www.youtube.com/channel/UCQkEQzoAfWckA2L1wSvL0mw" target="_blank"><i class="fa fa-youtube fa-2" aria-hidden="true"></i></a>
                        <a class="mr-3 ml-3" href="https://github.com/Azurras" target="_blank"><i class="fa fa-github fa-2" aria-hidden="true"></i></a>
                        <a class="mr-3 ml-3" href="https://www.linkedin.com/in/christopher-bell1813" target="_blank"><i class="fa fa-linkedin fa-2" aria-hidden="true"></i></a>
                    </div>
                </div>
            </div>
        );
    } 
}
