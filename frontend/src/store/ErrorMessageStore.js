import {EventEmitter} from 'events';
import {default as dispatcher} from '../dispatcher/Dispatcher';
import * as actionConstants from '../dispatcher/ComplexNumberActionConstants'
import * as costumerConstants from '../dispatcher/CustomerActionConstants'
import * as reservationActionConstants from '../dispatcher/ReservationActionConstants'
import * as slotActionConstants from '../dispatcher/SlotActionConstants'

class ErrorMessageStore extends EventEmitter {

    _errorMsg = "";

    emitChange() {
        this.emit('Change');
    }

    addOnChangeListener(callback) {
        this.addListener('Change', callback);
    }

    removeOnChangeListener(callback) {
        this.removeListener('Change', callback);
    }
}

const store = new ErrorMessageStore();
export default store;

dispatcher.register(({action, payload}) => {
    console.log({action: action, payload: payload});
    if (action !== actionConstants.showError) {
        return;
    }
    store._errorMsg = payload;
    store.emitChange();
});

dispatcher.register(({action}) => {
    if (action !== actionConstants.clearError) return;
    store._errorMsg = "";
    store.emitChange();
});

dispatcher.register(({action, payload}) => {
    console.log({action: action, payload: payload});
    if (action !== costumerConstants.showError) {
        return;
    }
    store._errorMsg = payload;
    store.emitChange();
});

dispatcher.register(({action}) => {
    if (action !== costumerConstants.clearError) return;
    store._errorMsg = "";
    store.emitChange();
});

dispatcher.register(({action, payload}) => {
    if (action !== slotActionConstants.showError) return;
    store._errorMsg = payload;
    store.emitChange();
});

dispatcher.register(({action}) => {
    if (action !== slotActionConstants.clearError) return;
    store._errorMsg = "";
    store.emitChange();
});

dispatcher.register(({action, payload}) => {
    if (action !== reservationActionConstants.showError) return;
    store._errorMsg = payload;
    store.emitChange();
});

dispatcher.register(({action}) => {
    if (action !== reservationActionConstants.clearError) return;
    store._errorMsg = "";
    store.emitChange();
});
