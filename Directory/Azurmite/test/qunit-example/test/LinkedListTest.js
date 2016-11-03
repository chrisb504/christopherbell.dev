/*
    @author Greg Degruy
    1/21/2015

    This project uses QUnit for unit tests and firebug for debugging
    @see http://qunitjs.com/cookbook/#automating-unit-testing
    
    Open "LinkedListTest.html" in a browser, the open the developer 
    console show console.log output. All other ouput is rendered on the page.
*/


/*
    QUnit test structure

    QUnit.module("Module Name"); <- optional
    QUnit.test("Test Name", function(assert) {
        // code ...
    });
*/

/*global QUnit, LinkedList*/

(function LinkedListTestSuite() {

    "use strict";

    QUnit.module("Constructor");
    QUnit.test("Linkedlist Constructor", function (assert) {

        console.log("=== Constructor Module ===");
        
        // defines num asserts expected, ensures no assertions are missed
        assert.expect(2);

        
        var linkedList = new LinkedList();
        
        console.log(linkedList);

        assert.deepEqual(linkedList.length, 0, "0 Length");
        assert.deepEqual(linkedList.head, null, "Null Head");
    });

    QUnit.module("Append");
    QUnit.test("Append Integer", function (assert) {

        console.log("=== Append Module ===");

        var linkedList = new LinkedList();
        var max = 10;

        for (var i = 0; i < 10; i++) { linkedList.append(i); };

        console.log("Append Test");
        console.log(linkedList);

        assert.deepEqual(linkedList.length, max, "All nodes added");
    });

    QUnit.module("Remove");
    QUnit.test("Remove Integer", function (assert) {

        console.log("=== Remove Module ===");

        var linkedList = new LinkedList();

        for (var i = 0; i < 10; i++) { linkedList.append(i); };

        console.log("Initial Linkedlist");
        console.log(linkedList);

        linkedList.remove(0);
        linkedList.remove(linkedList.length - 1);
        linkedList.remove(5);

        console.log("Mutated Linkedlist, nodes with data 1, 9, and 6 should be removed");
        console.log(linkedList);

        assert.deepEqual(linkedList.length, 7, "Length reduced");
    });

}());
