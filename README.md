# 🖋️ InkMatch

**InkMatch** is a full-stack tattoo artist and studio marketplace platform where customers can discover verified tattoo artists and studios, browse tattoo portfolios, request consultations, and book appointments online.

This project is designed as a **startup-style marketplace system** rather than a simple CRUD booking website, with role-based dashboards, booking workflows, portfolio management, and admin moderation.

---

## 🚀 Features

### 👤 Multi-Role Authentication
- Customer login and registration
- Artist login and registration
- Studio owner login and registration
- Admin access
- Secure JWT-based authentication

### 🎨 Artist & Studio Discovery
- Browse tattoo artists and studios
- Search and filter by:
  - Tattoo style
  - Location
  - Rating
  - Price range
  - Verified status

### 🖼️ Portfolio Showcase
- Artists can upload tattoo portfolios
- Multiple images per portfolio
- Portfolio categorization by tattoo style

### 💬 Consultation System
- Customers can request tattoo consultations
- Upload tattoo reference images
- Artists can review and respond

### 📅 Booking Management
- Request tattoo appointments
- Booking status flow:
  - Pending
  - Accepted
  - Confirmed
  - Completed
  - Cancelled

### ⭐ Reviews & Ratings
- Customers can leave reviews for artists
- Rating system for artist credibility

### ❤️ Favorites / Wishlist
- Save favorite tattoo artists and works

### 🔔 Notifications
- Booking and consultation notifications
- Status update alerts

### 🛡️ Admin Moderation
- Verify tattoo artists and studios
- Manage users
- Review verification requests
- Moderate platform content

---

## 🏗️ Tech Stack

### Frontend
- **Next.js**
- **React**
- **Tailwind CSS**
- **Shadcn UI**
- **Axios**

### Backend
- **Spring Boot**
- **Spring Security**
- **JWT Authentication**
- **Spring Data JPA / Hibernate**
- **MySQL**

### External Services
- **Cloudinary** – Image storage
- **Brevo / Mailtrap** – Email notifications
- **Swagger UI** – API documentation

### Dev Tools
- **GitHub**
- **Postman**
- **IntelliJ IDEA**
- **VS Code**
- **MySQL Workbench / XAMPP**

---

## 📂 Project Structure

### Backend Structure
```bash
server/
└── src/main/java/com/inkmatch/server
    ├── config
    ├── controller
    ├── dto
    ├── entity
    ├── enums
    ├── exception
    ├── repository
    ├── security
    ├── service
    └── seed
```

### Frontend Structure
```bash
client/
├── app
├── components
├── hooks
├── lib
├── services
├── types
└── utils
```

---

## 🧠 System Roles

### Customer
- Register / login
- Browse artists and studios
- View portfolios
- Request consultations
- Book appointments
- Leave reviews
- Save favorites

### Artist
- Create and manage profile
- Upload portfolio
- View consultation requests
- Manage bookings
- Receive reviews
- Request verification

### Studio Owner
- Manage studio profile
- Showcase studio details
- Handle bookings and consultations
- Request verification

### Admin
- Manage platform users
- Approve or reject verification requests
- Moderate artists and studios
- Monitor system activity

---

## 🗃️ Database Design (Main Tables)

- `users`
- `artist_profiles`
- `studios`
- `portfolios`
- `portfolio_images`
- `consultations`
- `bookings`
- `reviews`
- `favorites`
- `notifications`
- `verification_requests`

---

## 🔐 Authentication & Authorization

InkMatch uses **JWT-based authentication** with **role-based authorization**.

### Main Auth Features
- User registration
- Secure password hashing using BCrypt
- Login with JWT token generation
- Protected API routes
- Role-based access control

---

## 🌐 Main API Groups

### Authentication
- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/auth/me`

### Artists
- `GET /api/artists`
- `GET /api/artists/{id}`
- `POST /api/artists/profile`
- `PUT /api/artists/profile`

### Studios
- `GET /api/studios`
- `GET /api/studios/{id}`
- `POST /api/studios`
- `PUT /api/studios/{id}`

### Portfolios
- `POST /api/portfolios`
- `GET /api/portfolios`
- `GET /api/portfolios/{id}`
- `PUT /api/portfolios/{id}`
- `DELETE /api/portfolios/{id}`

### Consultations
- `POST /api/consultations`
- `GET /api/consultations/my`
- `PUT /api/consultations/{id}/reply`

### Bookings
- `POST /api/bookings`
- `GET /api/bookings/my`
- `PUT /api/bookings/{id}/status`

### Reviews
- `POST /api/reviews`
- `GET /api/reviews/artist/{artistId}`

### Favorites
- `POST /api/favorites/{artistId}`
- `GET /api/favorites/my`
- `DELETE /api/favorites/{artistId}`

### Admin
- `GET /api/admin/users`
- `GET /api/admin/verifications`
- `PUT /api/admin/verifications/{id}/approve`
- `PUT /api/admin/verifications/{id}/reject`

---

## ⚙️ Installation & Setup

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/your-username/inkmatch.git
cd inkmatch
```

---

## 🖥️ Backend Setup

### 2️⃣ Go to Backend Folder
```bash
cd server
```

### 3️⃣ Configure Database
Create a MySQL database:

```sql
CREATE DATABASE inkmatch_db;
```

### 4️⃣ Update `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/inkmatch_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 5️⃣ Run Backend
```bash
./mvnw spring-boot:run
```

---

## 💻 Frontend Setup

### 6️⃣ Go to Frontend Folder
```bash
cd client
```

### 7️⃣ Install Dependencies
```bash
npm install
```

### 8️⃣ Run Frontend
```bash
npm run dev
```

---

## ☁️ Deployment Plan

### Frontend
- **Vercel**

### Backend
- **Render / Railway**

### Database
- **Railway MySQL / Aiven MySQL**

### Image Hosting
- **Cloudinary**

---
 

## 📌 Future Improvements

- Real-time chat between customer and artist
- Availability calendar
- Recommendation system based on tattoo style
- Payment integration
- Artist analytics dashboard
- Mobile-responsive polishing
- Booking reminders via email

---

## 🎯 Project Goal

The goal of InkMatch is to build a **real-world marketplace platform** that demonstrates:

- Full-stack development skills
- Role-based system design
- Authentication and authorization
- Image/file handling
- Booking and consultation workflows
- Clean architecture and scalable backend design

 

 
