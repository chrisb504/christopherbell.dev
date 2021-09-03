import React, { Component } from 'react';

export default class Footer extends Component {
    render() {
        return (
            <div class="container text-center">
                <div class="row">
                    <div class="col">
                        <div class="fw-bold">Christopher Bell</div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <a href="mailto:cbell7@icloud.com" target="_blank">cbell7@icloud.com</a>
                    </div>
                </div>
                <div class="row">
                    <div class="col py-2">
                        <a class="mr-3 ml-3" href="https://www.youtube.com/channel/UCQkEQzoAfWckA2L1wSvL0mw" target="_blank"><i class="px-2 fa fa-youtube fa-2" aria-hidden="true"></i></a>
                        <a class="mr-3 ml-3" href="https://github.com/Azurras" target="_blank"><i class="px-2 fa fa-github fa-2" aria-hidden="true"></i></a>
                    </div>
                </div>
            </div>
        );
    } 
}
