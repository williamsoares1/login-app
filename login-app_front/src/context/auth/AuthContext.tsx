import React, { createContext, useContext, useEffect, useState } from 'react';
import { login_api } from '../../service/login_api/login_api';
import { useNavigate } from 'react-router-dom';

const AuthContext = createContext({});

const AuthProvider = ({children}) => {
    const navigate = useNavigate();
    const [user, setUser] = useState();

    useEffect(() => {
        localStorage.setItem('user', JSON.stringify(user));
    }, [user]);
    
    const findUser = async () => {
    try {
        const response = await login_api.post("/auth/refresh-token");
        setUser(response.data);
        navigate("/home")
    } catch (error) {
        console.log(error)
    }
    }

    return (
        <AuthContext.Provider value={{user, setUser, findUser}}>
            {children}
        </AuthContext.Provider>
    );
}

export {AuthContext, AuthProvider};

export const useUser = () => {
    return useContext(AuthContext);
};
