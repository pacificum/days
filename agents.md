# Pacificum Project Structure

## Overview
This is a Play Framework application (Scala 3.3.1) that serves a peace-focused website with multiple content sections and multilingual support.

## Project Structure

### Main Components

#### Controllers
- **AppRoutes.scala** - Main controller handling all routes
  - Home page routing
  - Stories/Days content routing  
  - Prize/Award content routing
  - PeaceBook routing
  - Refugee content routing
  - Lights/Statements routing
  - Event routing
  - Asset serving

#### Content Modules

##### Pages Framework (`app/light/pages/`)
- **Page.scala** - Base page traits and interfaces
- **AssetDir.scala** - Asset directory management

##### Days Module (`app/light/days/`)
- **DaysMain.scala** - Main days section controller
- **Days.scala** - Days content aggregator
- **DayRecord.scala** - Individual day record structure
- Content types: Stories, Heroes, Music, Movies, GraphicArt

##### Static Export Module (`app/exportsite/`)
- **ExportController.scala** - Play Framework controller for website export
  - HTTP endpoint: `GET /export` for triggering website export
  - Configurable parameters (output directory, domain, local mode)
  - Asynchronous export processing with result reporting
  - Uses HTTP client to fetch real page content instead of generating placeholders
- **ExportService.scala** - Core export service implementation
  - Uses WSClient for HTTP requests to fetch actual rendered pages
  - Generates static HTML files for all website content
  - Copies static assets (CSS, JS, fonts, icons)
  - Supports multiple languages and content types
  - Creates complete directory structure for static site
  - Handles 404 errors gracefully for non-existent pages
- **ExportApp.scala** - Command-line utility for export operations
  - CLI interface for website export functionality
  - Usage: `sbt "runMain exportsite.ExportApp [outputDir] [domain] [enableLocal]"`
  - Export features: home page, main pages, day records, prize content, lights content
  - Excludes refugee pages, maintains complete static asset structure
  - Uses Pekko ActorSystem and WSClient for HTTP operations

#### Routes Structure
```
GET /                             -> Home page
GET /export                       -> Website export functionality
GET /days/:key/                   -> Day stories
GET /:page/                       -> General pages
```

#### Views Structure
- Twirl templates in `app/views/`
- Organized by content type (days, lights, award, etc.)
- Multilingual support (ru, en, ua)

#### Assets
- Bootstrap CSS/JS framework
- Custom CSS (main.css, lights.css, etc.)
- Web fonts (FuturaPT, NeuchaRegular)
- Icons and images

## Key Features
- Responsive design with Bootstrap
- Asset management and optimization
- Local development mode with query parameter enabling
- Subdomain routing for different content sections
- Date-based content organization for stories
- **Website Export Functionality**
  - HTTP-based export using real page content (not placeholders)
  - Complete static site generation with all assets
  - Both HTTP endpoint and CLI interface available
  - Graceful handling of non-existent pages (404 errors)
  - Configurable output directory and domain settings

## Build Configuration
- Scala 3.3.1
- Play Framework
- Twirl templating
- Asset pipeline with digest and gzip compression
- WebJars for frontend dependencies