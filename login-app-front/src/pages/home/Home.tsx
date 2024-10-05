import { useEffect } from 'react';
import { useUser } from '../../context/auth/AuthContext';
import { InterceptorComp } from '../../service/login_api/intercptor';
import "../../css/homePage.css"

export const Home = () => {
    const {user, session, testeAPI, logout} = useUser();

    InterceptorComp();

    useEffect(() => {
        if(!user){
            session("/home");
        }
    }, []);

    const handleTeste = async (e: React.FormEvent) => {
        e.preventDefault();
    
        testeAPI()
    };

    const handleLogout = async (e: React.FormEvent) => {
        e.preventDefault();
    
        logout()
    };

    return (
        <>
            <div className='homeContainer'>
                <h2>{user?.nome}</h2>
                <h2>{user?.cargo}</h2>
                <div className='homeButtonsContainer'>
                    <button className='testeButton' onClick={handleTeste}>TESTE</button>
                    <button className='logoutButton' onClick={handleLogout}>LOGOUT</button>
                </div>
            </div>
        </>
    );
}
