import {EventEmitter} from 'events'
import dispatcher from "../dispatcher/Dispatcher";
import {refreshMap, refreshSlots} from '../dispatcher/SlotActionConstants';

class SlotStore extends EventEmitter {

    _map = [];
    _freeSlots = [];

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

const store = new SlotStore();
export default store;

dispatcher.register(({action, payload}) => {
    if (action !== refreshMap) return;
    store._map = payload;
    store.emitChange();
})

dispatcher.register(({action, payload}) => {
    if (action !== refreshSlots) return;
    store._freeSlots = payload;
    store.emitChange();
})
