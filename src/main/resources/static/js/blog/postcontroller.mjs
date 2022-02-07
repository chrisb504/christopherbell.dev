import BlogPost from './blogpost.mjs';


function getTags() {
    return $('input#input-tags').val().replace(/\s/g, '');
}

function setBlogPost() {
    let newBlogPost = new BlogPost($('input#input-title').val(),
        $('input#input-author').val(), $('input#input-date').val(),
        $('textarea#input-post').val());

    newBlogPost.tags = getTags();
    return newBlogPost;
}

function postBlogPost(posting) {
    $.ajax({
        type: 'POST',
        data: posting,
        url: `${posting.location}/add`,
        dataType: 'JSON'
    }).done((response) => {
        if (response.msg === '') {
            // Clear the form inputs
            $('input').val('');
            alert('Blog Posted');
        } else {
            alert(`Error: ${response.msg}`);
        }
    });
}

export function deletePostWithID(event) {
    event.preventDefault();
    const confirmation = confirm('Are you sure you want to delete this post?');

    if (confirmation === true) {
        $.ajax({
            type: 'DELETE',
            url: `/blog/post/delete/${$('#input-blog-id').val()}`
        }).done((response) => {
            alert("Restaurant Deleted");
            $('input').val('');
            if (response.msg === '') {} else {
                alert('Error: ' + response.msg);
            }
        });
    } else {
        return false;
    }
}

function validateBlogPost() {
    // Super basic validation - increase errorCount variable if any fields are blank
    let errorCount = 0;
    $('#input').each(function (index, val) {
        if ($(this).val() === '') {
            errorCount += 1;
        }
    });
    return errorCount;
}

export function addBlogPost(event) {
    event.preventDefault();
    if (validateBlogPost() === 0) {
        let posting = setBlogPost();
        console.log(posting);
        postBlogPost(posting);
    } else {
        // If errorCount is more than 0, error out
        alert('Please fill in all fields');
        return false;
    }
}