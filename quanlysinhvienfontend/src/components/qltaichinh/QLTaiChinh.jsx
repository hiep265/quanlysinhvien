import { useEffect, useState } from "react";
import "./QLTaiChinh.css"

export default function QLTaiChinh() {
    const [users, setUsers] = useState([]); 
    const [user, setUser] = useState(null); 

    
    const getUsers = async () => {
        try {
            const response = await fetch("http://localhost:8082/api/user/users");
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            const data = await response.json();
            setUsers(data.data);
        } catch (error) {
            console.error('Lỗi khi lấy danh sách user:', error);
        }
    }

    
    useEffect(() => {
        const fetchData = async () => {
            await getUsers();
        }
        fetchData();
    }, []);

    
    const handleSelectChange = async (userID) => {
        if (!userID) return; 
        try {
            const response = await fetch(`http://localhost:8082/api/tien/quan_ly/${userID}`);
            if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);
            const user = await response.json();
            setUser(user.data);
        } catch (error) {
            console.error("Lỗi khi lấy thông tin user:", error);
        }
    }

    return (
        <div id="container">
            <h1 className="header-title">Quản Lý Tài Chính</h1>

            <div className="form-container">
                <label className="label" htmlFor="userSelect">Lựa chọn User:</label>
                <select 
                    id="userSelect" 
                    className="select-user" 
                    onChange={(e) => handleSelectChange(e.target.value)} 
                    defaultValue=""
                >
                    <option value="">-- Chọn User --</option>
                    {users.map((user) => (
                        <option key={user.userID} value={user.userID}>
                            User ID: {user.userID} - User name: {user.username}
                        </option>
                    ))}
                </select>
            </div>

            {user && (
                <div className="user-info-container">
                    <h2 className="info-title">Thông tin tài chính của {user.username}</h2>
                    <div className="user-info">
                        <p>
                            <label className="label">Tên học viên:</label> 
                            <span className="info-value">{user.username}</span>
                        </p>
                        <p>
                            <label className="label">Số dư:</label> 
                            <span className="info-value">{user.soDu}</span>
                        </p>
                        <p>
                            <label className="label">Công nợ:</label> 
                            <span className="info-value">{user.congNo}</span>
                        </p>
                        <p>
                            <label className="label">Cần thanh toán:</label> 
                            <span className="info-value">{user.canThanhToan}</span>
                        </p>
                    </div>
                </div>
            )}
        </div>
    )
}
