import React, { useEffect, useState } from 'react';
import { useUser } from '../../context/auth/AuthContext';

export const Home = () => {
    const {user, findUser} = useUser();

    useEffect(() => {
        findUser();
    }, )

    return (
        <div style={{color: "white"}}>{user && user.nome}</div>
    )
}
