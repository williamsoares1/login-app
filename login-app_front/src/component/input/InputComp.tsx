import "../../css/components/inputComp.css";
import "../../css/components/inputCompCommon.css";
import { InputCompProps } from '../../@types/InputCompProps';

export const Input_component = ({value, setValue, placeholder, max, label, type}: InputCompProps) => {
    return (
        <div className='input_container'>
            <label htmlFor={label}>{label}</label>
            <input className='input_box' id={label} type={type} value={value} placeholder={placeholder} maxLength={max} onChange={(e) => setValue(e.target.value)}/>
        </div>
    )
}
