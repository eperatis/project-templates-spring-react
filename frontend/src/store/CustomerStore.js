import {EventEmitter} from 'events'
import dispatcher from "../dispatcher/Dispatcher";
import {refresh} from '../dispatcher/CustomerActionConstants';


class CustomerStore extends EventEmitter{

    _customers = [];

    emitChange(){
        this.emit('Change');
    }

    addChangeListener(callback){
        this.addListener('Change',callback);
    }

    removeChangeListener(callback){
        this.removeListener('Change',callback);
    }
}

const store = new CustomerStore();
export default store;

dispatcher.register(({action,payload}) => {
    if(action !== refresh ) return;
    store._customers = payload;
    store.emitChange();
})