import { useEffect } from 'react';
import { useUser } from '../../context/auth/AuthContext';

export const Home = () => {
    const {user, refresh, testeAPI, logout} = useUser();

    useEffect(() => {
        refresh("/home");
    }, [])

    const handleTeste = async (e: React.FormEvent) => {
        e.preventDefault();
    
        testeAPI()
    }

    const handleLogout = async (e: React.FormEvent) => {
        e.preventDefault();
    
        logout()
    }

    return (
        <>
            <div style={{color: "white"}}>{user && user.nome}</div>
            <button onClick={handleTeste}>TESTE</button>
            <button onClick={handleLogout}>LOGOUT</button>
        </>
    )
}
