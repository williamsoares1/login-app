import React from 'react';
import "../../css/components/default_input_component.css";
import "../../css/components/input_component_common.css";

export const Input_component = ({value, setValue, placeholder, max, label, type}) => {
    return (
        <div className='input_container'>
            <label htmlFor={label}>{label}</label>
            <input className='input_box' id={label} type={type} value={value} placeholder={placeholder} maxLength={max} onChange={(e) => setValue(e.target.value)}/>
        </div>
    )
}
