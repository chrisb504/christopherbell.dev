/*
    @author Greg Degruy
    1/21/2015
*/

function LinkedList() {

    this.length = 0;
    this.head = null;

    LinkedList.prototype.append = function (d) {

        "use strict";

        var position, node;

        node = { data: d, next: null };

        if (this.head === null) {
            this.head = node;
            this.length++;

        } else {
            position = this.head;
            while (position.next) { // true if has next
                position = position.next;
            }
            position.next = node;
            this.length++;
        }
    };

    LinkedList.prototype.remove = function (i) {

        "use strict";

        var position, tempNode;
        position = this.head;

        if (i === 0) {
            this.head = this.head.next;
            this.length--;

        } else if (i === this.length-1) {
            while (position.next) {
                tempNode = position;
                position = position.next;
            }
            tempNode.next = null;
            this.length--;

        } else {
            for(var j = 0; j < i; j++) {
                tempNode = position;
                position = position.next;
            }
            tempNode.next = position.next;
            this.length--;
        }
    };

    // V TODO: Finish me V
    LinkedList.prototype.get = function (i) {

        "use strict";

        var position = this.head;

        for(var j = 0; j < i; j++) {
            position = position.next;
        }

        return position;
    };
}
