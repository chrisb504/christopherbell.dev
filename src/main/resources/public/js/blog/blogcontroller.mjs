import * as post from './postcontroller.mjs';

$(document).ready(() => {
    if ($('#blog-button-add').length > 0) {
        $('#blog-button-add').on('click', post.addBlogPost);
    }

    if ($('#blog-button-delete').length > 0) {
        $('#blog-button-delete').on('click', post.deletePostWithID);
    }
});