export default class BlogPost {
    constructor(title, author, date, post) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.post = post;
        this.location = '/blog/post';
    }

    printAll() {
        console.log('Title', this.title);
        console.log('Author', this.author);
        console.log('Date', this.date);
    }
}
