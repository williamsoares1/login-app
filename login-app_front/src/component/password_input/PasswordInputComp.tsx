import React, { useState } from 'react';
import "../../css/components/passwordInputComp.css";
import "../../css/components/inputCompCommon.css";
import { IoEyeOff } from "react-icons/io5";
import { IoEyeSharp } from "react-icons/io5";
import { InputCompProps } from '../../@types/InputCompProps';

export const Password_input = ({value, setValue, placeholder, max, label, type}: InputCompProps) => {
    const [visible, setVisible] = useState(false);

    const handleVisibilityClick = (e: React.FormEvent<HTMLButtonElement>) => {
        e.preventDefault();

        setVisible(!visible);
        console.log("AAA")
    }

    return (
        <div className='input_container'>
            <label htmlFor={label}>{label}</label>
            <div className='input_box'>
                <input id={label} type={visible ? "text" : type} value={value} placeholder={placeholder} maxLength={max} onChange={(e) => setValue(e.target.value)}/>
                <button onClick={(e) => handleVisibilityClick(e)}>{visible ? <IoEyeOff color='var(--branco)' size={18} /> : <IoEyeSharp color='var(--branco)' size={18} />
            }</button>
            </div>
        </div>
    )
}