import { createContext, ReactNode, useContext, useState } from 'react';
import { login_api } from '../../service/login_api/login_api';
import { useNavigate } from 'react-router-dom';
import { AuthContextProps, LoginObject, RegisterObject, User } from '../../@types/AuthContextProps';

const AuthContext = createContext<AuthContextProps | undefined>(undefined);

const AuthProvider = ({children}: {children: ReactNode}) => {
    const navigate = useNavigate();
    const [user, setUser] = useState<User>();

    const session = async (pagePath: string | null) => {
        try {
            const response = await login_api.get("/auth/session");
            setUser(response.data);
            pagePath && navigate(pagePath);
        } catch (error) {
            console.log(error);
        }
    };

    const register = async (registerObject: RegisterObject) => {
        try {
            const response = await login_api.post("/auth/register", registerObject);
            setUser(response.data);
            navigate("/");
        } catch (error) {
            console.log(error)
        }
    };

    const login = async (loginObject: LoginObject) => {
        try {
            const response = await login_api.post("/auth/login", loginObject);
            setUser(response.data);
            navigate("/home");
        } catch (error) {
            console.log(error)
        }
    };

    const logout = async () => {
        try {
            await login_api.post("/auth/logout");
            setUser(undefined);
            navigate("/");
        } catch (error) {
            console.log(error)
        }
    };

    const testeAPI = async () => {
        try {
            const response = await login_api.get("/teste");
            window.alert(response.data);
        } catch (error) {
            window.alert("Sem permissão");
        }
    };

    return (
        <AuthContext.Provider value={{user, setUser, login, testeAPI, logout, register, session}}>
            {children}
        </AuthContext.Provider>
    );
}

export {AuthContext, AuthProvider};

export const useUser = () => {
    const context = useContext(AuthContext);

    if (!context) {
        throw new Error('undefined');
    }

    return context;
};
