import { useEffect, useState } from "react";
import "../KetQuaHP.css";
export default function KetQuaHP() {
    const [users, setUsers] = useState([]);
    const [userID, setUserID] = useState("");
    const [hocki, setHocki] = useState();
    const [GPA, setGPA] = useState();

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
    };

    useEffect(() => {
        const fetchData = async () => {
            await getUsers();
        };
        fetchData();
    }, []);

    const handleSelectChange = async (userID) => {
        if (!userID) return;
        setUserID(userID);
        try {
            const response = await fetch(`http://localhost:8082/api/ket_qua/ket_qua_GPA/${userID}`);
            if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);
            const diem = await response.json();
            setGPA(diem.data);
        } catch (error) {
            console.error("Lỗi khi lấy thông tin user:", error);
        }
    };

    const handleSelectChange2 = async (num) => {
        if (!num) return;
        try {
            const response = await fetch(`http://localhost:8082/api/ket_qua/ket_qua_1_hoc_ky/${num}/${userID}`);
            if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);
            const data = await response.json();
            setHocki(data.data);
        } catch (error) {
            console.error("Lỗi khi lấy thông tin user:", error);
        }
    };

    return (
        <div className="container">
            <h1 className="title">Kết quả học phần</h1>
            <div className="select-container">
                <label className="label" htmlFor="userSelect">Lựa chọn User:</label>
                <select
                    id="userSelect"
                    className="select"
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

            {GPA && (
                <div className="gpa-container">
                    <h3 className="gpa-title">GPA của sinh viên là: {GPA}</h3>
                    <select
                        id="semesterSelect"
                        className="select"
                        onChange={(e) => handleSelectChange2(e.target.value)}
                        defaultValue=""
                    >
                        <option value="">-- Chọn Học Kỳ --</option>
                        {[1, 2, 3, 4, 5, 6, 7].map((num) => (
                            <option key={num} value={num}>{num}</option>
                        ))}
                    </select>
                </div>
            )}

            {hocki && (
                <div className="result-container">
                    <table className="result-table">
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Tên Học Phần</th>
                                <th>Tên Giáo Viên</th>
                                <th>TX1</th>
                                <th>TX2</th>
                                <th>Điểm cuối kì</th>
                                <th>Tổng kết</th>
                            </tr>
                        </thead>
                        <tbody>
                            {hocki.map((ket_qua) => (
                                <tr key={ket_qua.id}>
                                    <td>{ket_qua.id}</td>
                                    <td>{ket_qua.tenHocPhan}</td>
                                    <td>{ket_qua.tenGiaoVien}</td>
                                    <td>{ket_qua.tx1}</td>
                                    <td>{ket_qua.tx2}</td>
                                    <td>{ket_qua.diem}</td>
                                    <td>{ket_qua.tongKet}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
}
