import { useEffect, useState } from "react";

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
        <div className="container mt-4">
            <h1 className="text-center mb-4">Thông Tin Tài Chính</h1>

            <div className="mb-4">
                <label className="form-label" htmlFor="userSelect">Lựa chọn User:</label>
                <select 
                    id="userSelect" 
                    className="form-select" 
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
                <div className="mt-4">
                    <h2 className="mb-3">Thông tin tài chính của {user.username}</h2>
                    <div className="list-group">
                        <p className="list-group-item">
                            <strong>Tên học viên:</strong> <span>{user.username}</span>
                        </p>
                        <p className="list-group-item">
                            <strong>Số dư:</strong> <span>{user.soDu}</span>
                        </p>
                        <p className="list-group-item">
                            <strong>Công nợ:</strong> <span>{user.congNo}</span>
                        </p>
                        <p className="list-group-item">
                            <strong>Cần thanh toán:</strong> <span>{user.canThanhToan}</span>
                        </p>
                    </div>
                </div>
            )}
        </div>
    );
}
