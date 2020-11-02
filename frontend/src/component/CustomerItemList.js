import React from 'react';

function CustomerItemList({address,firstName,lastName,phoneNumber}){
    return (
        <span>({address},{firstName},{lastName},{phoneNumber})</span>
    );
}

export default CustomerItemList;