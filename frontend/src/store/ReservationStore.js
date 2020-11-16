import {EventEmitter} from 'events'
import dispatcher from '../dispatcher/Dispatcher'
import {refresh} from '../dispatcher/ReservationActionConstants';

class ReservationStore extends EventEmitter {

    _reservations = [];

    emitChange() {
        this.emit('Change');
    }

    addChangeListener(callback) {
        this.addListener('Change', callback);
    }

    removeChangeListener(callback) {
        this.removeListener('Change', callback);
    }
}

const store = new ReservationStore();
export default store;

dispatcher.register(({action, payload}) => {
    if (action !== refresh) return;
    store._reservations = payload;
    store.emitChange();
})
