import React, { Component } from 'react';

export default class Image extends Component {
    constructor(props) {
        super(props);
        this.state = {
            images: [],
            location: '/service/images',
        };
        this.getData = this.getData.bind(this);
    }

    componentDidMount() {
        this.getData(this.state.location);
    }

    componentDidUpdate() {
    }

    getData(fetchLocation) {
        console.log("Getting data at ", fetchLocation);
        fetch(fetchLocation)
            // passes the response and returns json from response
            .then(res => res.json())
            // posts gets that return from the last then and puts in it state
            .then((galleryImagesResponse) => {
                let images = galleryImagesResponse.images;
                this.setState({ images });
            });
    }

    render() {
        return (
            <div className="container-fluid">
                <div class="row row-cols-sm-1 row-cols-md-2 g-2">
                {this.state.images.map(image => (
                    <div class="col">
                        <img src={image.path} class="img-fluid rounded" alt=""></img>
                    </div>
                ))}
                </div>
            </div>
        );
    }
}
