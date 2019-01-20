import BlogPost from './blogpost.mjs';


function setBlogPost() {
    let newBlogPost = new BlogPost($('input#input-title').val(),
        $('input#input-author').val(), $('input#input-author').val(),
        $('#input#input-post').val());

    return newBlogPost;
}

function postBlogPost(posting) {
    console.log(posting);
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
        postBlogPost(posting);
    } else {
        // If errorCount is more than 0, error out
        alert('Please fill in all fields');
        return false;
    }
}
