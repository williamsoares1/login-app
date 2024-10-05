import React, { useState } from 'react';
import { useUser } from '../../context/auth/AuthContext';
import { FaUserAstronaut } from 'react-icons/fa';
import { Input_component } from '../../component/input/InputComp';
import '../../css/formPages.css';
import { useNavigate } from 'react-router-dom';

export const RegisterPage = () => {
    const [nome, setNome] = useState();
    const [email, setEmail] = useState();
    const [senha, setSenha] = useState();
    const [cargo, setCargo] = useState();
    const {register} = useUser();
    const navigate = useNavigate();

    const registerObject = {
        nome,
        email,
        senha,
        cargo
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();

        register(registerObject);
    };

    return (
    <>
        <main>
            <section>
                <FaUserAstronaut size={100} color='var(--laranja)' />
                <h1>REGISTER</h1>
            </section>
            <section>
                <form onSubmit={handleSubmit}>
                    <Input_component max={12} placeholder={"username"} type={"text"} value={nome} setValue={setNome} label={"nome"}/>
                    <Input_component max={40} placeholder={"ex: user@email.com"} type={"text"} value={email} setValue={setEmail} label={"email"}/>
                    <Input_component max={12} placeholder={"senha"} type={"text"} value={senha} setValue={setSenha} label={"senha"}/>
                    <Input_component max={12} placeholder={"ADM ou USUARIO"} type={"text"} value={cargo} setValue={setCargo} label={"cargo"}/>
                    <button type='submit'>SUBMIT</button>
                </form>
                <div className='redirect'>
                    <h2>Já possui uma conta? <span onClick={() => navigate("/")}>Login</span></h2>
                </div>
            </section>
        </main>
    </>
    )
}
