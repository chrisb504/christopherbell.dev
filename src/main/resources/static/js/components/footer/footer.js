import React, { Component } from 'react';
import ReactDOM from 'react-dom';

/**
 * This component represents the footer on each page.
 */
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

/* 
    This seems to be important from my findings. If the page doesn't include that component,
    then it will still try to render without the EventListener. This will cause React to throw
    an error about not finding the DOM item. This method prevents the render function from
    loading if the component isn't on the page.

    I think there might be a better solution to this. I think that solution might include
    not including the js for smaller components in Main.js
*/
window.addEventListener('load', () => {
    if (document.body.contains(document.getElementById('footer'))) {
        // Used to render the component on the page. The default refresh is when state is changed.
        ReactDOM.render(<Footer />, document.getElementById('footer'));
    }
});
