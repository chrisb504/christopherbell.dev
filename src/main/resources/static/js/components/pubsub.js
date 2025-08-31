class PubSub {
    constructor() {
        this.events = {};
    }

    subscribe(event, handler) {
        if (!this.events[event]) {
            this.events[event] = [];
        }
        this.events[event].push(handler);
    }

    publish(event, data) {
        if (this.events[event]) {
            this.events[event].forEach((handler) => handler(data));
        }
    }
}

const pubsub = new PubSub();
export default pubsub;
